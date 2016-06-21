package com.preteur.ach.model;

public class Transfer {

    private String transferId;
    private String sourceUserId;
    private String destUserId;
    private int value;
    private String currency;
    private String notes;

    public Transfer(String transferId, String sourceUserId, String destUserId, int value, String currency, String notes) {
        this.transferId = transferId;
        this.sourceUserId = sourceUserId;
        this.destUserId = destUserId;
        this.value = value;
        this.currency = currency;
        this.notes = notes;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(String sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public String getDestUserId() {
        return destUserId;
    }

    public void setDestUserId(String destUserId) {
        this.destUserId = destUserId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
