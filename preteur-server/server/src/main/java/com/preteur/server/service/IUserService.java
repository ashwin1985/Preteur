package com.preteur.server.service;

import com.preteur.server.dto.ResponseBody;
import com.preteur.server.dto.User;
import rx.Observable;

import java.util.List;

public interface IUserService {

    public Observable<Boolean> createUser(String ipaddress, User user);
    public Observable<List<String>> getAllUsers();
}
