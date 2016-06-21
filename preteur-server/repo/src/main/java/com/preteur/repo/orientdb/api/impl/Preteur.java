package com.preteur.repo.orientdb.api.impl;

import com.preteur.repo.orientdb.api.IPreteur;
import com.preteur.repo.orientdb.dao.PreteurDao;
import com.preteur.repo.orientdb.dto.UserDto;
import com.preteur.repo.orientdb.model.Relations;
import com.preteur.repo.orientdb.result.Result;

import java.math.BigDecimal;

public class Preteur implements IPreteur {

    PreteurDao dao;

    public Preteur() {
        this.dao = new PreteurDao();
    }

    public Preteur(PreteurDao idWrapper) { this.dao = idWrapper; }

    @Override
    public Result<Boolean> createUser(UserDto userDto) {
        return dao.createUser(userDto);
    }

    @Override
    public Result<Boolean> addUserToCircle(String primaryUser, String secondUser, Relations.NetworkTypes networkType) {
        return dao.updateNetwork(primaryUser, secondUser, networkType);
    }

    @Override
    public Result<Boolean> addBondBetweenUsers(String lender, String borrower, int principle) {
        return dao.createBond(lender,borrower,principle);
    }

}
