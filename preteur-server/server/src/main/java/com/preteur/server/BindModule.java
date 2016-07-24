package com.preteur.server;

import com.google.inject.AbstractModule;
import com.preteur.repo.orientdb.api.IPreteur;
import com.preteur.repo.orientdb.api.impl.Preteur;
import com.preteur.server.handler.AuthHandler;
import com.preteur.server.handler.OptionHandler;
import com.preteur.server.handler.TokenHandler;
import com.preteur.server.handler.UserHandler;
import com.preteur.server.service.IAuthService;
import com.preteur.server.service.IUserService;
import com.preteur.server.service.impl.AuthService;
import com.preteur.server.service.impl.UserService;

public class BindModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IPreteur.class).toInstance(new Preteur());
        bind(IUserService.class).to(UserService.class);
        bind(IAuthService.class).to(AuthService.class);
        bind(OptionHandler.class);
        bind(AuthHandler.class);
        bind(UserHandler.class);
        bind(TokenHandler.class);
    }
}
