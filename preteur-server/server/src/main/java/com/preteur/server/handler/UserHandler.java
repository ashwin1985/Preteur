package com.preteur.server.handler;

import javax.inject.Inject;
import com.preteur.server.dto.User;
import com.preteur.server.mapper.ResponseMapper;
import com.preteur.server.service.IUserService;
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
        new ResponseMapper().handleResponse(ctx, iuser.createUser(user), false);
    }

    private void getAllUsers(Context ctx) {
        new ResponseMapper().handleResponse(ctx, iuser.getAllUsers(), false);
    }

}
