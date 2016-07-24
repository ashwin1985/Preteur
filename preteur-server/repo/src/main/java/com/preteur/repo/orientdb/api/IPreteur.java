package com.preteur.repo.orientdb.api;

import com.preteur.repo.orientdb.dto.TokenInfo;
import com.preteur.repo.orientdb.dto.UserDto;
import com.preteur.repo.orientdb.model.Relations;
import com.preteur.repo.orientdb.model.User;
import com.preteur.repo.orientdb.result.Result;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface IPreteur {

    public Result<Boolean> createUser(User user);
    public Result<Object> getUserProperty(String phoneNumber, String propertyName);
    public Result<TokenInfo> getUserTokenInfo(String phoneNumber);
    public Result<Boolean> updateUserTokenInfo(String phoneNumber, TokenInfo tokenInfo);
    public Result<Boolean>
        addUserToCircle(String firstUser, String secondUser,
                        Relations.NetworkTypes networkType);
    public Result<Boolean>
        addBondBetweenUsers(String lender, String borrower, int principle);

}