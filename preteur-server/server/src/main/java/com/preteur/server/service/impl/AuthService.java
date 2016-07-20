package com.preteur.server.service.impl;

import com.preteur.repo.orientdb.api.IPreteur;
import com.preteur.repo.orientdb.result.Result;
import com.preteur.server.service.IAuthService;
import com.preteur.tauth.Authorize;

import javax.inject.Inject;
import java.security.SecureRandom;

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

        return password.equals(ps);
    }

    @Override
    public boolean tokenAuthentication(String userName, String token) {
        Result<Object> secretResult = ipreteur.getUserProperty(userName, "secret");

        if(!secretResult.isStatus()) {
            System.out.println(secretResult.getFailureReason());
            return false;
        }

        return new Authorize().validateToken(token, (byte[]) secretResult.getResult());
    }

    @Override
    public String createToken(String userName) {
        byte[] secret = new byte[64];

        SecureRandom random = new SecureRandom();
        random.nextBytes(secret);

        String token = new Authorize().issueToken(userName, secret);

        Result<Boolean> result = ipreteur.updateUserSecret(userName, secret);

        if(!result.isStatus()) {
            System.out.println(result.getFailureReason());
            token = null;
        }

        return token;
    }
}
