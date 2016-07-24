package com.preteur.repo.orientdb.dao;

import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

import com.preteur.ach.api.IAchClient;
import com.preteur.ach.dwolla.AchClient;
import com.preteur.ach.model.FundingSource;
import com.preteur.ach.model.Transfer;
import com.preteur.repo.orientdb.dto.AchUserDto;
import com.preteur.repo.orientdb.dto.TokenInfo;
import com.preteur.repo.orientdb.dto.UserDto;
import com.preteur.repo.orientdb.model.Relations;
import com.preteur.repo.orientdb.model.User;
import com.preteur.repo.orientdb.result.Result;
import com.preteur.repo.orientdb.transaction.TransactionalTemplate;
import com.preteur.repo.orientdb.util.Helper;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

import java.util.*;

public class PreteurDao {

    public Result<Boolean> createUser(User user) {
        Date now = new Date();
        user.setCreatedDate(now);
        user.setLastModifiedDate(now);

        TransactionalTemplate<Boolean> tr = new TransactionalTemplate<>();
        return tr.doWork(graph -> {
            Vertex v = graph.addVertex("class:User");

            Map<String, Object> map = Helper.convertObjectToMap(user);
            for (String k : map.keySet()) {
                v.setProperty(k, map.get(k));
            }

            graph.commit();

            return true;
        });
    }

    public Result<Boolean> updateUserTokenInfo(String phone, TokenInfo tokenInfo) {
        TransactionalTemplate<Boolean> tr = new TransactionalTemplate<>();
        return tr.doWork(graph -> {
            OCommandSQL q = new OCommandSQL("UPDATE User SET secret = ? " +
                    ", secretExpDate = ? , lastModifiedDate = ? " +
                    "WHERE phone = ?");
            graph.command(q).execute(tokenInfo.getSecret(),
                    tokenInfo.getSecretExpDate(), new Date(), phone);

            graph.commit();

            return true;
        });

    }

    public Result<TokenInfo> getUserTokenInfo(String phone) {
        TransactionalTemplate<TokenInfo> tr = new TransactionalTemplate<>();
        return tr.doWork(graph -> {
            OSQLSynchQuery q = new OSQLSynchQuery("SELECT FROM User " +
                    "WHERE phone = ?");
            Iterable<OrientVertex> users = graph.command(q).execute(phone);

            TokenInfo result = null;
            int count = 0;

            for(OrientVertex u : users) {
                if(count > 1) {
                    // throw exception
                }

                result = new TokenInfo((byte[]) u.getProperties().get("secret"),
                        (Date) u.getProperties().get("secretExpDate"));
                count++;
            }

            graph.commit();

            return result;
        });
    }

    public Result<Object> getUserProperty(String phone, String propertyName) {
        TransactionalTemplate<Object> tr = new TransactionalTemplate<>();
        return tr.doWork(graph -> {
            OSQLSynchQuery q = new OSQLSynchQuery("SELECT FROM User " +
                    "WHERE phone = ?");
            Iterable<OrientVertex> users = graph.command(q).execute(phone);

            Object result = null;
            int count = 0;

            for(OrientVertex u : users) {
                if(count > 1) {
                    // throw exception
                }

                result = u.getProperties().get(propertyName);
                count++;
            }

            graph.commit();

            return result;
        });
    }

    public Result<Boolean> updateNetwork(String primaryUser, String secondUser,
                                             Relations.NetworkTypes networkType) {
        TransactionalTemplate<Boolean> tr = new TransactionalTemplate<>();
        return tr.doWork(graph -> {
            OSQLSynchQuery q = new OSQLSynchQuery ("SELECT FROM User " +
                    "WHERE emailAddress = " +
                    "? OR emailAddress = ?");
            Iterable<OrientVertex> users= graph.command(q).execute(primaryUser, secondUser);

            graph.addEdge(null,users.iterator().next(), users.iterator().next(), networkType.name());

            graph.commit();

            return true;
        });
    }

    public Result<Boolean> createBond(String lender, String borrower, int principle) {
        TransactionalTemplate<Boolean> tr = new TransactionalTemplate<>();
        return tr.doWork(graph -> {
            OSQLSynchQuery q = new OSQLSynchQuery ("SELECT FROM User " +
                    "WHERE emailAddress = " +
                    "? OR emailAddress = ?");
            Iterable<OrientVertex> users= graph.command(q).execute(lender, borrower);

            OrientVertex lenderVertex = users.iterator().next();
            OrientVertex borrowerVertex = users.iterator().next();

            String lenderFSId = (String) lenderVertex.getProperties().get("achFundingSourcesId");
            String borrowerFSId = (String) borrowerVertex.getProperties().get("achUserId");

            if(lenderFSId == null) {
                throw new Exception("No lender funding source");
            }

            if(borrowerFSId == null) {
                throw new Exception("No borrower funding source");
            }

            //ACH calls
            initiateACHTransfer(lenderFSId, borrowerFSId, "Test Transfer", "USD", principle);

            Vertex v = graph.addVertex("class:Bond");

            Map<String, Object> map = new HashMap();
            map.put("principle",principle);
            map.put("dateCreated", new Date());

            graph.addEdge(null,users.iterator().next(), v, Relations.BondTypes.LENDER.name());

            graph.addEdge(null,users.iterator().next(), v, Relations.BondTypes.BORROWER.name());

            graph.commit();

            return true;
        });
    }

    private String initiateACHTransfer(String sourceUserFSId, String destUserFSId,
                                     String notes, String currency, int value) throws Exception {
        IAchClient client = new AchClient();
        Transfer t = new Transfer(null, sourceUserFSId, destUserFSId, value, currency, notes);
        t = client.doTransfer(t);

        return t.getTransferId();
    }

    private AchUserDto createACHUser(UserDto userDto) throws Exception {
        List<FundingSource> list = new ArrayList<>();
//        FundingSource fs = new FundingSource(null, userDto.getRoutingNumber(), userDto.getAccountNumber(),
//                userDto.getType(), userDto.getBankName());
//        list.add(fs);

        com.preteur.ach.model.User achUser =
                new com.preteur.ach.model.User(null,userDto.getUser().getFristName(),
                userDto.getUser().getLastName(), userDto.getUser().getEmailAddress(),
                userDto.getUser().getIpAddress(), "personal", userDto.getUser().getAddress1(),
                userDto.getUser().getAddress2(), userDto.getUser().getCity(),
                userDto.getUser().getState(), userDto.getUser().getPostalCode(),
                userDto.getUser().getDob(), userDto.getSsn(),
                userDto.getUser().getPhone(), list);

        IAchClient client = new AchClient();
        achUser = client.addUser(achUser);

        return new AchUserDto(achUser.getAchUserId(), new Date(), null);
    }

}
