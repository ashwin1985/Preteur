package com.preteur.ach.model;

import java.util.List;

public class User {

    private String achUserId;
    private String firstName;
    private String lastName;
    private String email;
    private String ipAddress;
    private String type;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
    private String dateOfBirth;
    private String ssn;
    private String phone;
    private List<FundingSource> fundingSources;

    public User(String achUserId, String firstName, String lastName, String email,
                String ipAddress, String type, String address1, String address2,
                String city, String state, String postalCode, String dateOfBirth,
                String ssn, String phone, List<FundingSource> fundingSources) {
        this.achUserId = achUserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.ipAddress = ipAddress;
        this.type = type;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.dateOfBirth = dateOfBirth;
        this.ssn = ssn;
        this.phone = phone;
        this.fundingSources = fundingSources;
    }

    public String getAchUserId() {
        return achUserId;
    }

    public void setAchUserId(String achUserId) {
        this.achUserId = achUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress1() {
        return address1;
    }

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<FundingSource> getFundingSources() {
        return fundingSources;
    }

    public void setFundingSources(List<FundingSource> fundingSources) {
        this.fundingSources = fundingSources;
    }
}
