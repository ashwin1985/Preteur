package com.preteur.repo.orientdb.api;

import com.preteur.repo.orientdb.dto.UserDto;
import com.preteur.repo.orientdb.model.Relations;
import com.preteur.repo.orientdb.model.User;
import com.preteur.repo.orientdb.result.Result;

import java.math.BigDecimal;

public interface IPreteur {

    public Result<Boolean> createUser(User user);
    public Result<Boolean> authenticate(String phoneNumber, String password);
    public Result<String> createToken(String phoneNumber);
    public Result<Boolean> addUserToCircle(String firstUser,
                                String secondUser, Relations.NetworkTypes networkType);
    public Result<Boolean> addBondBetweenUsers(String lender, String borrower, int principle);

}
