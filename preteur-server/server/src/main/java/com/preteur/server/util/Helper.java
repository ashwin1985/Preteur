package com.preteur.server.util;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.nio.charset.Charset;
import java.util.Base64;

public class Helper {

    public static  String[] parseAuthHeader(String auth, boolean token) {
        if(!token) {
            return parseAuthHeader(auth);
        }

        String values[] = null;

        if(auth != null && auth.startsWith("Bearer")) {
            String tokenString = auth.substring("Bearer ".length()).trim();
            String tokens[] = tokenString.split("\\.");
            String sub = null;

            if(tokens.length < 3) {
                System.out.println("Invalid token");
                return values;
            }

            try {
                JSONObject json = new JSONObject(new String(
                        Base64.getDecoder().decode(tokens[1]),
                        Charset.forName("UTF-8")));
                sub = (String) json.get("sub");
            } catch(JSONException e) {
                System.out.println("There is json parse exception");
                return values;
            }

            if(sub != null) {
                values = new String[] {sub, tokenString};
            } else {
                System.out.println("There is no sub in the token");
            }
        }

        return values;
    }

    private static  String[] parseAuthHeader(String auth) {
        String values[] = null;

        if (auth != null && auth.startsWith("Basic")) {
            String base64Credentials = auth.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                    Charset.forName("UTF-8"));

            values = credentials.split(":", 2);
        }

        return values;
    }

}
