package com.example.emailapplication.helpEntities;

public class Setting {
    public Long accountId;
    public boolean orderAscending;
    public float refreshTime;

    public Setting(Long accountId, boolean orderAscending, float refreshTime) {
        this.accountId = accountId;
        this.orderAscending = orderAscending;
        this.refreshTime = refreshTime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public boolean isOrderAscending() {
        return orderAscending;
    }

    public void setOrderAscending(boolean orderAscending) {
        this.orderAscending = orderAscending;
    }

    public float getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(float refreshTime) {
        this.refreshTime = refreshTime;
    }
}
