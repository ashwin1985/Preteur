package com.preteur.ach.api;

import com.preteur.ach.model.Transfer;
import com.preteur.ach.model.User;

public interface IAchClient {

    public Transfer doTransfer(Transfer transfer) throws Exception;

    public User addUser(User user) throws Exception;

}
