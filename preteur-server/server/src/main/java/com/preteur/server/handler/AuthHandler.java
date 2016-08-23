package com.preteur.server.handler;

import com.preteur.server.service.IAuthService;
import com.preteur.server.util.Helper;
import ratpack.exec.Execution;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;

public class AuthHandler implements Handler {

    IAuthService iauth;

    @Inject
    public AuthHandler(IAuthService iauth) {
        this.iauth = iauth;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Promise.async(downstream -> {
            Execution.fork().start(execution -> {
                downstream.success(doAuthentication(ctx.getRequest()
                        .getHeaders().get("Authorization")));
            });
        }).then(success -> {
            if((Boolean) success) {
                ctx.next();
            } else {
                ctx.getResponse().status(401).send();
            }
        });
    }

    private boolean doAuthentication(String auth) {
        String[] values = Helper.parseAuthHeader(auth, true);
        boolean success = false;

        if(values != null) {
            if(iauth.tokenAuthentication(values[0], values[1])) {
                success = true;
            }
        } else {
            values = Helper.parseAuthHeader(auth, false);
            if(values != null && values.length == 2) {
                if(iauth.basicAuthentication(values[0], values[1])) {
                    success = true;
                }
            }
        }

        return success;
    }
}
