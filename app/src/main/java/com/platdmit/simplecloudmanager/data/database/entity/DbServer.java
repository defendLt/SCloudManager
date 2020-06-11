package com.platdmit.simplecloudmanager.data.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DbServer {
    @PrimaryKey
    private long mId;

    private String mName;
    private int mMemory;
    private int mVcpus;
    private int mDisk;

    private String mRegion;
    private long mImage;

    private double mBackupPriceHourly;

    private String mPaymentDate;
    private String mPaymentAmount;
    private String mPaymentPeriod;
    private String mTotalHours;
    private String mWorkedHours;

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

    public DbServer(long id, String name, int memory, int vcpus, int disk, String region, long image, double backupPriceHourly, String paymentDate, String paymentAmount, String paymentPeriod, String totalHours, String workedHours, boolean locked, String status, String createdAt, String startedFirstAt, String startedAt, boolean isInstall, boolean isError, String password, String v4Ip, boolean mbit200) {
        mId = id;
        mName = name;
        mMemory = memory;
        mVcpus = vcpus;
        mDisk = disk;
        mRegion = region;
        mImage = image;
        mBackupPriceHourly = backupPriceHourly;
        mPaymentDate = paymentDate;
        mPaymentAmount = paymentAmount;
        mPaymentPeriod = paymentPeriod;
        mTotalHours = totalHours;
        mWorkedHours = workedHours;
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

    public long getImage() {
        return mImage;
    }

    public double getBackupPriceHourly() {
        return mBackupPriceHourly;
    }

    public String getPaymentDate() {
        return mPaymentDate;
    }

    public String getPaymentAmount() {
        return mPaymentAmount;
    }

    public String getPaymentPeriod() {
        return mPaymentPeriod;
    }

    public String getTotalHours() {
        return mTotalHours;
    }

    public String getWorkedHours() {
        return mWorkedHours;
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

    public String getV4Ip() {
        return mV4Ip;
    }

    public boolean isMbit200() {
        return mMbit200;
    }
}