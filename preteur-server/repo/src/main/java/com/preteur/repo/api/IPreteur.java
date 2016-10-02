package com.preteur.repo.api;

import com.preteur.repo.dto.TokenInfo;
import com.preteur.repo.model.Relations;
import com.preteur.repo.model.User;
import com.preteur.repo.result.Result;

import java.util.List;

public interface IPreteur {

    public Result<Boolean> createUser(User user);
    public Result<List<String>> getAllUsers();
    public Result<Object> getUserProperty(String phoneNumber, String propertyName);
    public Result<TokenInfo> getUserTokenInfo(String phoneNumber);
    public Result<Boolean> updateUserTokenInfo(String phoneNumber, TokenInfo tokenInfo);
    public Result<Boolean>
        addUserToCircle(String firstUser, String secondUser,
                        Relations.NetworkTypes networkType);
    public Result<Boolean>
        addBondBetweenUsers(String lender, String borrower, int principle);

}