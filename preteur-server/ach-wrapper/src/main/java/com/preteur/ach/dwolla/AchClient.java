package com.preteur.ach.dwolla;

import com.preteur.ach.api.IAchClient;
import com.preteur.ach.model.FundingSource;
import com.preteur.ach.model.Transfer;
import com.preteur.ach.model.User;
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.api.CustomersApi;
//import io.swagger.client.api.FundingsourcesApi;
//import io.swagger.client.api.TransfersApi;
//import io.swagger.client.model.*;

import java.util.HashMap;
import java.util.Map;

public class AchClient implements IAchClient{

    // ApiClient client;

    public AchClient() {
//        client = new ApiClient();
//        client.setBasePath("https://api-uat.dwolla.com/");
    }

//    public  AchClient(ApiClient client) {
//        this.client = client;
//    }

    @Override
    public Transfer doTransfer(Transfer transfer) throws Exception {
//        Map<String, HalLink> links = new HashMap<>();
//
//        HalLink sourceLink = new HalLink();
//        sourceLink.setHref(transfer.getSourceUserId());
//        links.put("source", sourceLink);
//
//        HalLink destLink = new HalLink();
//        destLink.setHref(transfer.getDestUserId());
//        links.put("destination", destLink);
//
//        io.swagger.client.model.Amount amt = new io.swagger.client.model.Amount();
//        amt.setCurrency(transfer.getCurrency());
//        amt.setValue(String.valueOf(transfer.getValue()));
//
//        Map<String,String> metadata = new HashMap();
//        metadata.put("notes", transfer.getNotes());
//
//        TransferRequestBody t = new TransferRequestBody();
//        t.setLinks(links);
//        t.setAmount(amt);
//        t.setMetadata(metadata);
//
//        transfer.setTransferId(new TransfersApi(client).create(t).getLocationHeader());
//
//        return transfer;

        return null;
    }

    @Override
    public User addUser(User user) throws Exception{
        return null; // return createCustomer(user);
    }

    private String getUserIdFromLocationHeader(String achUserId) {
        return achUserId.substring(achUserId.lastIndexOf("/") + 1);
    }

//    private User createCustomer(User user) throws ApiException {
//        CreateCustomer cust = new CreateCustomer();
//        cust.setType(user.getType());
//        cust.setFirstName(user.getFirstName());
//        cust.setLastName(user.getLastName());
//        cust.setEmail(user.getEmail());
//        cust.setPhone(user.getPhone());
//        cust.setPostalCode(user.getPostalCode());
//        cust.setAddress1(user.getAddress1());
//        cust.setAddress2(user.getAddress2());
//        cust.setCity(user.getCity());
//        cust.setState(user.getState());
//        cust.setPostalCode(user.getPostalCode());
//        cust.setIpAddress(user.getIpAddress());
//        cust.setSsn(user.getSsn());
//        cust.setDateOfBirth(user.getDateOfBirth());
//
//        user.setAchUserId(new CustomersApi(client)
//                .create(cust).getLocationHeader());
//
//        if (user.getFundingSources() != null) {
//            String userId = getUserIdFromLocationHeader(user.getAchUserId());
//
//            for (FundingSource fs : user.getFundingSources()) {
//                fs.setFsId(createFundingSourceForUser(userId, fs));
//            }
//        }
//
//        return user;
//    }
//
//    private String createFundingSourceForUser(String userId, FundingSource fs) throws ApiException {
//        CreateFundingSourceRequest fsr = new CreateFundingSourceRequest();
//        fsr.setAccountNumber(fs.getAccountNumber());
//        fsr.setRoutingNumber(fs.getRoutingNumber());
//        fsr.setName(fs.getBankName());
//        fsr.setType(fs.getType());
//
//        return new FundingsourcesApi(client).createCustomerFundingSource(fsr, userId).getLocationHeader();
//    }
}
