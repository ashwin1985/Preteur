package com.preteur.repo.dto;

import com.preteur.repo.model.User;

public class UserDto {

    private User user;
    private String ssn;
    private String routingNumber;
    private String accountNumber;
    private String type;
    private String bankName;

    public UserDto(User user, String ssn, String routingNumber, String accountNumber, String type, String bankName) {
        this.user = user;
        this.ssn = ssn;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.type = type;
        this.bankName = bankName;
    }

    public User getUser() { return user; }

    public String getSsn() {
        return ssn;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public String getBankName() {
        return bankName;
    }
}
