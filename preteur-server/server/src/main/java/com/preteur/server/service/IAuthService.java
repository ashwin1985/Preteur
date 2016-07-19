package com.preteur.server.service;

public interface IAuthService {

    public boolean basicAuthentication(String userName, String password);
    public String createToken(String userName);

}
