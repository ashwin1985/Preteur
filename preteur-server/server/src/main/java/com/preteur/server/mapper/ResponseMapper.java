package com.preteur.server.mapper;

import ratpack.handling.Context;
import ratpack.jackson.Jackson;
import rx.Observable;

public class ResponseMapper {

    public void handleResponse(Context ctx, Observable observable, boolean addResBody) {
        observable.subscribe(result -> validateResult(ctx, result, addResBody),
                err -> handleError(ctx, (Throwable) err));
    }

    private void handleError(Context ctx, Throwable err) {
        // TODO: log the error
        ctx.getResponse().status(500).send();
    }

    private void validateResult(Context ctx, Object result, boolean addResBody) {
        if(result == null) {
            ctx.getResponse().status(400).send();
        } else if(addResBody) {
            ctx.render(Jackson.json(result));
        } else {
            ctx.getResponse().status(200).send();
        }
    }

}
