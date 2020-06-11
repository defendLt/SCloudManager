package com.platdmit.simplecloudmanager.data.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DbAccount {
    @PrimaryKey
    private long mId;

    private boolean mMain;
    private boolean mSubAccount;

    private String mLogin;
    private String mEmail;
    private String mPass;
    private String mApiKey;

    private double mBalance;
    private String mPin;
    private int mVpsLimit;
    private int mNotificationCount;

    public DbAccount(long id, boolean subAccount, String login, String email, String pass, double balance, int vpsLimit) {
        mId = id;
        mSubAccount = subAccount;
        mLogin = login;
        mEmail = email;
        mPass = pass;
        mBalance = balance;
        mVpsLimit = vpsLimit;
    }

    public boolean isMain() {
        return mMain;
    }

    public void setMain(boolean main) {
        mMain = main;
    }

    public String getLogin() {
        return mLogin;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }

    public boolean isSubAccount() {
        return mSubAccount;
    }

    public double getBalance() {
        return mBalance;
    }

    public void setBalance(double balance) {
        mBalance = balance;
    }

    public int getVpsLimit() {
        return mVpsLimit;
    }

    public int getNotificationCount() {
        return mNotificationCount;
    }

    public void setNotificationCount(int notificationCount) {
        mNotificationCount = notificationCount;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getPass() {
        return mPass;
    }

    public String getPin() {
        return mPin;
    }

    public void setPin(String pin) {
        mPin = pin;
    }

    public void setPass(String pass) {
        mPass = pass;
    }
}
