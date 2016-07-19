package com.preteur.server.handler;

import com.preteur.server.service.IAuthService;
import com.preteur.server.util.Helper;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;

public class TokenHandler implements Handler {

    IAuthService iauth;

    @Inject
    public TokenHandler(IAuthService iauth) {
        this.iauth = iauth;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        if(ctx.getRequest().getMethod().isPost()) {
            String auth = ctx.getRequest().getHeaders().get("Authorization");
            String[] values = Helper.parseAuthHeader(auth);
            boolean success = false;

            if(values != null && values.length == 2) {
                String token = iauth.createToken(values[0]);
                if(token != null && !token.isEmpty()) {
                    success = true;
                    ctx.getResponse().status(200).send("application/json", token);
                }
            }

            if(!success) {
                ctx.getResponse().status(400).send();
            }
        }

    }
}