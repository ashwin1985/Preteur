package com.preteur.server.handler;

import com.preteur.server.mapper.ResponseMapper;
import com.preteur.server.service.IAuthService;
import com.preteur.server.util.Helper;
import ratpack.exec.Execution;
import ratpack.exec.Promise;
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

            if(ctx.getRequest().getPath().equals("login")) {
                login(ctx, auth);
            } else if(ctx.getRequest().getPath().equals("logout")) {
                logout(ctx, auth);
            }
        }
    }

    private void login(Context ctx, String auth) {
        String[] values = Helper.parseAuthHeader(auth, false);

        if(values != null && values.length == 2) {
            Promise.async(downstream ->
                    Execution.fork().start(execution ->
                            downstream.success(iauth.createToken(values[0]))))
                    .then(result -> new ResponseMapper()
                            .handleResponse(ctx, (rx.Observable) result, true));
        } else {
            ctx.getResponse().status(400).send();
        }
    }

    private void logout(Context ctx, String auth) {
        String[] values = Helper.parseAuthHeader(auth, true);

        if(values != null) {
            Promise.async(downstream ->
                    Execution.fork().start(execution ->
                            downstream.success(iauth.removeToken(values[0]))))
                    .then(result -> new ResponseMapper()
                            .handleResponse(ctx, (rx.Observable) result, false));
        } else {
            ctx.getResponse().status(400).send();
        }
    }
}
