package com.platdmit.simplecloudmanager.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiAccount {
    @SerializedName("uuid")
    @Expose
    private int uuid;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_verified")
    @Expose
    private boolean emailVerified;
    @SerializedName("is_legal")
    @Expose
    private boolean isLegal;
    @SerializedName("is_resident")
    @Expose
    private boolean isResident;
    @SerializedName("balance")
    @Expose
    private double balance;
    @SerializedName("mailing")
    @Expose
    private boolean mailing;
    @SerializedName("dfa")
    @Expose
    private boolean dfa;
    @SerializedName("onboarding")
    @Expose
    private boolean onboarding;
    @SerializedName("subaccounts_enabled")
    @Expose
    private boolean subaccountsEnabled;
    @SerializedName("vps_limit")
    @Expose
    private int vpsLimit;
    @SerializedName("is_subaccount")
    @Expose
    private boolean isSubaccount;
    @SerializedName("notifications")
    @Expose
    private String notifications;

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public void setLegal(boolean legal) {
        isLegal = legal;
    }

    public boolean isResident() {
        return isResident;
    }

    public void setResident(boolean resident) {
        isResident = resident;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isMailing() {
        return mailing;
    }

    public void setMailing(boolean mailing) {
        this.mailing = mailing;
    }

    public boolean isDfa() {
        return dfa;
    }

    public void setDfa(boolean dfa) {
        this.dfa = dfa;
    }

    public boolean isOnboarding() {
        return onboarding;
    }

    public void setOnboarding(boolean onboarding) {
        this.onboarding = onboarding;
    }

    public boolean isSubaccountsEnabled() {
        return subaccountsEnabled;
    }

    public void setSubaccountsEnabled(boolean subaccountsEnabled) {
        this.subaccountsEnabled = subaccountsEnabled;
    }

    public int getVpsLimit() {
        return vpsLimit;
    }

    public void setVpsLimit(int vpsLimit) {
        this.vpsLimit = vpsLimit;
    }

    public boolean isSubaccount() {
        return isSubaccount;
    }

    public void setSubaccount(boolean subaccount) {
        isSubaccount = subaccount;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }
}
