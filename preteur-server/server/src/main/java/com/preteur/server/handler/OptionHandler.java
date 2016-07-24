package com.preteur.server.handler;

import ratpack.handling.Context;
import ratpack.handling.Handler;

public class OptionHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        if(ctx.getRequest().getMethod().isOptions()) {
            System.out.println("Inside options handler!!!");
            ctx.getResponse().getHeaders()
                    .add("Access-Control-Allow-Headers", "Content-Type, Authorization")
                    .add("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                    .add("Access-Control-Allow-Origin", "*");
            ctx.getResponse().status(200).send();
        } else {
            ctx.getResponse().getHeaders().add("Access-Control-Allow-Origin","*");
            ctx.next();
        }
    }
}
