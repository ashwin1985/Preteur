package com.preteur.repo.dto;

import java.util.Date;

public class AchUserDto {

    private String achUserId;
    private Date achUserCreatedDate;
    private String achFundingSourcesId;

    public AchUserDto(String achUserId, Date achUserCreatedDate, String achFundingSourcesId) {
        this.achUserId = achUserId;
        this.achUserCreatedDate = achUserCreatedDate;
        this.achFundingSourcesId = achFundingSourcesId;
    }

    public String getAchUserId() {
        return achUserId;
    }

    public void setAchUserId(String achUserId) {
        this.achUserId = achUserId;
    }

    public Date getAchUserCreatedDate() {
        return achUserCreatedDate;
    }

    public void setAchUserCreatedDate(Date achUserCreatedDate) {
        this.achUserCreatedDate = achUserCreatedDate;
    }

    public String getAchFundingSourcesId() {
        return achFundingSourcesId;
    }

    public void setAchFundingSourcesId(String achFundingSourcesId) {
        this.achFundingSourcesId = achFundingSourcesId;
    }
}
