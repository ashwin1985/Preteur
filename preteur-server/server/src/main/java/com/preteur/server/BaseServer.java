package com.preteur.server;

import com.preteur.server.handler.AuthHandler;
import com.preteur.server.handler.OptionHandler;
import com.preteur.server.handler.TokenHandler;
import com.preteur.server.handler.UserHandler;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;

import java.net.URI;

public class BaseServer {

    public BaseServer() {}

    public void startServer() throws Exception {
        System.out.println("Started the server");
        RatpackServer.start(s -> s
                .serverConfig(c -> {
//                    c.ssl(SSLContexts.sslContext(new File(getClass().getClassLoader()
//                            .getResource("preteur.jks").getFile()), "xxxxxxxx"));
                    c.publicAddress(new URI("http://localhost:5050"));
                })
                .registry(Guice.registry(b -> b.module(BindModule.class)))
                .handlers(chain -> {
                            chain.all(OptionHandler.class);
                            chain.path("signup", UserHandler.class);
                            chain.all(AuthHandler.class);
                            chain.path("logout", TokenHandler.class);
                            chain.path("login", TokenHandler.class);
                            chain.path("hello", c -> { c.render("Hello World");
                                System.out.println("Executing hello handler"); });
                        }
                ));
    }

}
