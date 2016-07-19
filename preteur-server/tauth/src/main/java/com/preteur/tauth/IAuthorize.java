package com.preteur.tauth;

public interface IAuthorize {

    public String issueToken(String phone, byte[] secret);
    public boolean validateToken(String token, byte[] secret);

}
