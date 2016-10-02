package com.preteur.repo.dto;

import java.util.Date;

public class TokenInfo {

    private byte[] secret;
    private Date secretExpDate;

    public TokenInfo() {}

    public TokenInfo(byte[] secret, Date secretExpDate) {
        this.secret = secret;
        this.secretExpDate = secretExpDate;
    }

    public byte[] getSecret() {
        return secret;
    }

    public Date getSecretExpDate() {
        return secretExpDate;
    }
}
