package com.preteur.server.service.impl;

import com.preteur.repo.orientdb.api.IPreteur;
import com.preteur.repo.orientdb.dto.TokenInfo;
import com.preteur.repo.orientdb.result.Result;
import com.preteur.server.dto.ResponseBody;
import com.preteur.server.service.IAuthService;
import com.preteur.server.util.PreteurException;
import com.preteur.tauth.Authorize;
import rx.Observable;

import javax.inject.Inject;
import java.security.SecureRandom;
import java.util.Date;

public class AuthService implements IAuthService {

    IPreteur ipreteur;

    @Inject
    public AuthService(IPreteur ip) {
        this.ipreteur = ip;
    }

    @Override
    public boolean basicAuthentication(String userName, String password) {
        Result<Object> usersResult = ipreteur.getUserProperty(userName, "password");

        if (!usersResult.isStatus()) {
            System.out.println("Something went wrong in querying the DB");
            return false;
        }

        String ps = (String) usersResult.getResult();

        if(ps == null) {
            System.out.println("No user exists for phone number");
            return false;
        }

        return new Authorize().isPasswordValid(password, ps);
    }

    @Override
    public boolean tokenAuthentication(String userName, String token) {
        Result<TokenInfo> secretResult = ipreteur.getUserTokenInfo(userName);

        if(!secretResult.isStatus()) {
            System.out.println(secretResult.getFailureReason());
            return false;
        }

        if(secretResult.getResult() == null
                || secretResult.getResult().getSecret() == null) {
            System.out.println("No secret key");
            return false;
        }

        if(secretResult.getResult().getSecretExpDate() == null) {
            System.out.println("Token Expired");
            return false;
        }

        if(secretResult.getResult().getSecretExpDate().getTime()
                < new Date().getTime()) {
            System.out.println("Token Expired");
            removeToken(token);
            return false;
        }

        return new Authorize().validateToken(token, secretResult.getResult().getSecret());
    }

    @Override
    public Observable<ResponseBody<String>> createToken(String userName) {
        byte[] secret = new byte[64];

        SecureRandom random = new SecureRandom();
        random.nextBytes(secret);

        String token = new Authorize().issueToken(userName, secret);

        Date expiry = new Date(new Date().getTime() + (1800 * 1000));

        return Observable.just(ipreteur.updateUserTokenInfo(userName,
                new TokenInfo(secret, expiry))).map(result -> {

            if(!result.isStatus()) {
                //TODO: Log the error and move the message to properties file
                System.out.println(result.getFailureReason());
                throw new PreteurException("Creating token failed");
            }

            return new ResponseBody<>(token);
        });
    }

    @Override
    public Observable<Boolean> removeToken(String userName) {
        return Observable.just(ipreteur.updateUserTokenInfo(userName,
                new TokenInfo())).map(result -> {

            if(!result.isStatus()) {
                //TODO: Log the error and move the message to properties file
                System.out.println(result.getFailureReason());
                throw new PreteurException("Deleting token failed");
            }

            return true;
        });
    }
}
