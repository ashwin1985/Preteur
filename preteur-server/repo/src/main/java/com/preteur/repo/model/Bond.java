package com.preteur.repo.model;

import java.util.Date;

public class Bond {
    
    private int principle;
    private Date dateCreated;
    private String achTransferId;

    public int getPrinciple() {
        return principle;
    }

    public void setPrinciple(int principle) {
        this.principle = principle;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAchTransferId() {
        return achTransferId;
    }

    public void setAchTransferId(String achTransferId) {
        this.achTransferId = achTransferId;
    }
}
