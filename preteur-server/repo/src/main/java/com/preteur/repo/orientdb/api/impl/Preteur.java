package com.preteur.repo.orientdb.api.impl;

import com.preteur.repo.orientdb.api.IPreteur;
import com.preteur.repo.orientdb.dao.PreteurDao;
import com.preteur.repo.orientdb.dto.TokenInfo;
import com.preteur.repo.orientdb.model.Relations;
import com.preteur.repo.orientdb.model.User;
import com.preteur.repo.orientdb.result.Result;

public class Preteur implements IPreteur {

    PreteurDao dao;

    public Preteur() {
        this.dao = new PreteurDao();
    }

    public Preteur(PreteurDao idWrapper) { this.dao = idWrapper; }

    @Override
    public Result<Boolean> createUser(User user) {
        return dao.createUser(user);
    }

    @Override
    public Result<Object> getUserProperty(String phoneNumber, String propertyName) {
        return dao.getUserProperty(phoneNumber,propertyName);
    }

    @Override
    public Result<TokenInfo> getUserTokenInfo(String phoneNumber) {
        return dao.getUserTokenInfo(phoneNumber);
    }

    @Override
    public Result<Boolean> updateUserTokenInfo(String phoneNumber, TokenInfo tokenInfo) {
        return dao.updateUserTokenInfo(phoneNumber, tokenInfo);
    }

    @Override
    public Result<Boolean> addUserToCircle(String primaryUser,
                                           String secondUser,
                                           Relations.NetworkTypes networkType) {
        return dao.updateNetwork(primaryUser, secondUser, networkType);
    }

    @Override
    public Result<Boolean> addBondBetweenUsers(String lender,
                                               String borrower,
                                               int principle) {
        return dao.createBond(lender,borrower,principle);
    }

}
