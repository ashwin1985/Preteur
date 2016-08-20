package com.preteur.server.service;

import com.preteur.server.dto.ResponseBody;
import com.preteur.server.dto.User;

import java.util.List;

public interface IUserService {

    public boolean createUser(User user);
    public ResponseBody<List<String>> getAllUsers();
}
