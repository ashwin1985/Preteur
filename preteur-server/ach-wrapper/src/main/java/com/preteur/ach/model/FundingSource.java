package com.preteur.ach.model;

public class FundingSource {

    private String fsId;
    private String routingNumber;
    private String accountNumber;
    private String type;
    private String bankName;

    public FundingSource(String fsId, String routingNumber, String accountNumber, String type, String bankName) {
        this.fsId = fsId;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.type = type;
        this.bankName = bankName;
    }

    public String getFsId() {
        return fsId;
    }

    public void setFsId(String fsId) {
        this.fsId = fsId;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
