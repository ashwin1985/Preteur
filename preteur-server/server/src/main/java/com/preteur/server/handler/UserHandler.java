package com.preteur.server.handler;

import javax.inject.Inject;
import com.preteur.server.dto.User;
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
        if(ctx.getRequest().getMethod().isPost()) {
            ctx.parse(User.class).then(u -> {
                if(iuser.createUser(u)) {
                    ctx.getResponse().getHeaders().add("Access-Control-Allow-Origin","*");
                    ctx.getResponse().status(200).send();
                } else {
                    ctx.getResponse().status(400).send();
                }
            });

        }
    }
}
