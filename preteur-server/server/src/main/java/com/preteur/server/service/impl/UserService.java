package com.preteur.server.service.impl;

import javax.inject.Inject;
import com.preteur.repo.orientdb.api.IPreteur;
import com.preteur.repo.orientdb.result.Result;
import com.preteur.server.dto.User;
import com.preteur.server.service.IUserService;

public class UserService implements IUserService {

    IPreteur ipreteur;

    @Inject
    public UserService(IPreteur ip) {
        this.ipreteur = ip;
    }

    @Override
    public boolean createUser(User user) {
        Result<Boolean> result = ipreteur.createUser(userToRepoUser(user));

        if(!result.isStatus()) {
            System.out.println(result.getFailureReason());
        }

        return result.isStatus();
    }

    public com.preteur.repo.orientdb.model.User userToRepoUser(User user) {
        com.preteur.repo.orientdb.model.User ru = new com.preteur.repo.orientdb.model.User();
        ru.setFristName(user.getFirstName());
        ru.setLastName(user.getLastName());
        ru.setIpAddress("");
        ru.setPassword(user.getPassword());
        ru.setPhone(user.getPhone());
        ru.setEmailAddress(user.getEmail());

        return ru;
    }
}
