package com.preteur.server.handler;

import javax.inject.Inject;
import com.preteur.server.dto.User;
import com.preteur.server.mapper.ResponseMapper;
import com.preteur.server.service.IUserService;
import ratpack.exec.Execution;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;

public class UserHandler implements Handler{

    IUserService iuser;

    @Inject
    public UserHandler(IUserService iuser) {
        this.iuser = iuser;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.byMethod(methodSpec -> {
            methodSpec.post(() -> ctx.parse(User.class)
                    .then(user -> createUser(ctx, user)));

            methodSpec.get(() -> getAllUsers(ctx));
        });
    }

    private void createUser(Context ctx, User user) {
        //TODO: validate the user request
        Promise.async(downstream ->
                Execution.fork().start(execution ->
                        downstream.success(iuser.createUser(user))))
                .then(result -> new ResponseMapper()
                        .handleResponse(ctx, (rx.Observable) result, false));
    }

    private void getAllUsers(Context ctx) {
        Promise.async(downstream ->
                Execution.fork().start(execution ->
                        downstream.success(iuser.getAllUsers())))
                .then(result -> new ResponseMapper()
                        .handleResponse(ctx, (rx.Observable) result, true));
    }

}
