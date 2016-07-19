package com.preteur.server.service.impl;

import com.preteur.repo.orientdb.api.IPreteur;
import com.preteur.repo.orientdb.result.Result;
import com.preteur.server.service.IAuthService;

import javax.inject.Inject;

public class AuthService implements IAuthService {

    IPreteur ipreteur;

    @Inject
    public AuthService(IPreteur ip) {
        this.ipreteur = ip;
    }

    @Override
    public boolean basicAuthentication(String userName, String password) {
        Result<Boolean> result = ipreteur.authenticate(userName,password);

        if(!result.isStatus()) {
            System.out.println(result.getFailureReason());
        }

        return result.isStatus();
    }

    @Override
    public String createToken(String userName) {
        Result<String> result = ipreteur.createToken(userName);

        if(!result.isStatus()) {
            System.out.println(result.getFailureReason());
        }

        return result.getResult();
    }
}
