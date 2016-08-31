package com.preteur.server.handler;

import javax.inject.Inject;
import com.preteur.server.dto.User;
import com.preteur.server.mapper.ResponseMapper;
import com.preteur.server.service.IUserService;
import ratpack.exec.Execution;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.server.PublicAddress;
import sun.net.spi.nameservice.dns.DNSNameService;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

    private void createUser(Context ctx, User user) throws UnknownHostException {
        //TODO: validate the user request
        PublicAddress address = ctx.get(PublicAddress.class);
        String clientHost = address.get().getHost();
        InetAddress inetAddress = InetAddress.getByName(clientHost);
        String ipAddress = inetAddress.getHostAddress();
        Promise.async(downstream ->
                Execution.fork().start(execution ->
                        downstream.success(iuser.createUser(ipAddress, user))))
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

    public static void main(String args[]) throws UnknownHostException {
        InetAddress host = InetAddress.getByName("google.com");
        System.out.println(host.getHostAddress());
    }

}
