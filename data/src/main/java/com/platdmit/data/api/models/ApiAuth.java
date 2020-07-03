package com.platdmit.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiAuth {
    @SerializedName("account")
    @Expose
    private ApiRequestBody account;

    @SerializedName("session_key")
    @Expose
    private String sessionKey;

    public ApiRequestBody getAccount() {
        return account;
    }

    public void setAccount(ApiRequestBody account) {
        this.account = account;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
