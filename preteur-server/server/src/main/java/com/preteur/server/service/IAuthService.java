package com.preteur.server.service;

import com.preteur.server.dto.ResponseBody;
import rx.Observable;

public interface IAuthService {

    public boolean basicAuthentication(String userName, String password);
    public boolean tokenAuthentication(String userName, String token);
    public Observable<ResponseBody<String>> createToken(String userName);
    public Observable<Boolean> removeToken(String userName);

}
