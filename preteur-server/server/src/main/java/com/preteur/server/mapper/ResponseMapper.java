package com.preteur.server.mapper;

import ratpack.handling.Context;
import rx.Observable;

public class ResponseMapper {

    public void handleResponse(Context ctx, Observable observable, boolean addResBody) {
        observable.subscribe(result -> {
                    if(addResBody) {
                        ctx.getResponse().status(200).send();
                    }
                    else {
                        ctx.getResponse().status(200).send();
                    }
                },
                err -> handleError(ctx, (Throwable) err));
    }

    private void handleError(Context ctx, Throwable err) {
        // TODO: log the error
        ctx.getResponse().status(500).send();
    }

}
