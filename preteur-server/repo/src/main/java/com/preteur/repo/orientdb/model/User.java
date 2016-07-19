package com.preteur.repo.orientdb.model;

import java.util.Date;

public class User {
    
    private String fristName;
    private String lastName;
    private String company;
    private String phone;
    private String emailAddress;
    private String dob;
    private String password;
    private byte[] secret;
    private String ipAddress;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
    
    private Date createdDate;
    private Date lastModifiedDate;

    private String achUserId;
    private Date achUserCreatedDate;
    private String achFundingSourcesId;

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public byte[] getSecret() { return secret; }

    public void setSecret(byte[] secret) { this.secret = secret; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() { return ipAddress; }

    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getAddress1() { return address1; }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getAchUserId() { return achUserId; }

    public void setAchUserId(String achUserId) {
        this.achUserId = achUserId;
    }

    public Date getAchUserCreatedDate() {
        return achUserCreatedDate;
    }

    public void setAchUserCreatedDate(Date achUserCreatedDate) {
        this.achUserCreatedDate = achUserCreatedDate;
    }

    public String getAchFundingSourcesId() { return achFundingSourcesId; }

    public void setAchFundingSourcesId(String achFundingSourcesId) {
        this.achFundingSourcesId = achFundingSourcesId;
    }
}
