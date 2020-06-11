package com.platdmit.simplecloudmanager.domain.models;

public class UserAccount {
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

    public UserAccount(String login, String pass) {
        mLogin = login;
        mPass = pass;
    }

    public UserAccount(long id, boolean main, boolean subAccount, String login, String pass, double balance, int vpsLimit, String pin) {
        mId = id;
        mMain = main;
        mSubAccount = subAccount;
        mLogin = login;
        mPass = pass;
        mBalance = balance;
        mVpsLimit = vpsLimit;
        mPin = pin;
    }

    public long getId() {
        return mId;
    }

    public boolean isMain() {
        return mMain;
    }

    public boolean isSubAccount() {
        return mSubAccount;
    }

    public String getLogin() {
        return mLogin;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPass() {
        return mPass;
    }

    public String getApiKey() {
        return mApiKey;
    }

    public double getBalance() {
        return mBalance;
    }

    public String getPin() {
        return mPin;
    }

    public int getVpsLimit() {
        return mVpsLimit;
    }

    public int getNotificationCount() {
        return mNotificationCount;
    }


    public void setEmail(String email) {
        mEmail = email;
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }

    public void setPin(String pin) {
        mPin = pin;
    }

    public void setNotificationCount(int notificationCount) {
        mNotificationCount = notificationCount;
    }

    public void setMain(boolean main) {
        mMain = main;
    }
}