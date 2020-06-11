package com.platdmit.simplecloudmanager.domain.models;

import java.util.List;

public class Server {
    private long mId;

    private String mName;
    private int mMemory;
    private int mVcpus;
    private int mDisk;

    private String mRegion;
    private Image mImage;

    private double mBackupPriceHourly;

    private String mPaymentDate;
    private String mPaymentPrice;
    private String mUptime;

    private boolean mLocked;
    private String mStatus;

    private String mCreatedAt;
    private String mStartedFirstAt;
    private String mStartedAt;

    private boolean mIsInstall;
    private boolean mIsError;

    private String mPassword;

    private String mV4Ip;

    private boolean mMbit200;

    private List<Action> mServerLog;

    public Server(long id, String name, String uptime, String status) {
        mId = id;
        mName = name;
        mUptime = uptime;
        mStatus = status;
    }

    public Server(long id, String name, int memory, int vcpus, int disk, String region, double backupPriceHourly, String paymentDate, String paymentPrice, String uptime, boolean locked, String status, String createdAt, String startedFirstAt, String startedAt, boolean isInstall, boolean isError, String password, String v4Ip, boolean mbit200) {
        mId = id;
        mName = name;
        mMemory = memory;
        mVcpus = vcpus;
        mDisk = disk;
        mRegion = region;
        mBackupPriceHourly = backupPriceHourly;
        mPaymentDate = paymentDate;
        mPaymentPrice = paymentPrice;
        mUptime = uptime;
        mLocked = locked;
        mStatus = status;
        mCreatedAt = createdAt;
        mStartedFirstAt = startedFirstAt;
        mStartedAt = startedAt;
        mIsInstall = isInstall;
        mIsError = isError;
        mPassword = password;
        mV4Ip = v4Ip;
        mMbit200 = mbit200;
    }

    public void setImage(Image image) {
        mImage = image;
    }

    public void setServerLog(List<Action> serverLog) {
        mServerLog = serverLog;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getMemory() {
        return mMemory;
    }

    public int getVcpus() {
        return mVcpus;
    }

    public int getDisk() {
        return mDisk;
    }

    public String getRegion() {
        return mRegion;
    }

    public Image getImage() {
        return mImage;
    }

    public double getBackupPriceHourly() {
        return mBackupPriceHourly;
    }

    public String getPaymentDate() {
        return mPaymentDate;
    }

    public String getPaymentPrice() {
        return mPaymentPrice;
    }

    public String getUptime() {
        return mUptime;
    }

    public boolean isLocked() {
        return mLocked;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public String getStartedFirstAt() {
        return mStartedFirstAt;
    }

    public String getStartedAt() {
        return mStartedAt;
    }

    public boolean isInstall() {
        return mIsInstall;
    }

    public boolean isError() {
        return mIsError;
    }

    public String getPassword() {
        return mPassword;
    }

    public boolean isMbit200() {
        return mMbit200;
    }

    public String getV4Ip() {
        return mV4Ip;
    }

    public List<Action> getServerLog() {
        return mServerLog;
    }
}
