package com.preteur.server.service.impl;

import javax.inject.Inject;

import com.preteur.repo.orientdb.api.IPreteur;
import com.preteur.repo.orientdb.result.Result;
import com.preteur.server.dto.ResponseBody;
import com.preteur.server.dto.User;
import com.preteur.server.service.IUserService;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    IPreteur ipreteur;

    @Inject
    public UserService(IPreteur ip) {
        this.ipreteur = ip;
    }

    @Override
    public Observable<Boolean> createUser(User user) {
        return Observable.just(ipreteur.createUser(userToRepoUser(user))).map(result -> {
            if(!result.isStatus()) {
                //TODO: log the message using logger & throw exception
                System.out.println(result.getFailureReason());
            }

            return true;
        });
    }

    @Override
    public Observable<List<String>> getAllUsers() {
        return Observable.just(ipreteur.getAllUsers()).map(result -> {
            if(!result.isStatus()) {
                //TODO: log the message using logger & throw exception
                System.out.println(result.getFailureReason());
            }

            return result.getResult();
        });
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
