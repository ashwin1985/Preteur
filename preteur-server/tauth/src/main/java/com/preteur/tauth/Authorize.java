package com.preteur.tauth;

import io.jsonwebtoken.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class Authorize implements IAuthorize {

    @Override
    public String issueToken(String phone, byte[] secret) {
        SecretKey key = new SecretKeySpec(secret, SignatureAlgorithm.HS512.getJcaName());

        return Jwts.builder()
                .setSubject(phone)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public boolean validateToken(String token, byte[] secret) {
        SecretKey key = new SecretKeySpec(secret, SignatureAlgorithm.HS512.getJcaName());
        boolean result = true;

        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        } catch (SignatureException e) {
            // log the exception
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    public String getHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isPasswordValid(String password, String storedPassword) {
        return BCrypt.checkpw(password, storedPassword);
    }



}
