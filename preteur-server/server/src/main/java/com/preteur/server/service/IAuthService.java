package com.preteur.server.service;

public interface IAuthService {

    public boolean basicAuthentication(String userName, String password);
    public boolean tokenAuthentication(String userName, String token);
    public String createToken(String userName);

}
