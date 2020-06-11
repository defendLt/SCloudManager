package com.platdmit.simplecloudmanager.domain.models;

import java.util.List;

public class Backup {
    private long mId;
    private long mServer;
    private String mStatus;
    private String mName;
    private String mDistribution;
    private String mSlug;
    private boolean mPublic;
    private String mCreatedAt;
    private String mMinDiskSize;
    private String mComment;
    private String mTime;
    private String mPriceHourly;
    private long mAction;
    private String mSystem;

    public Backup(long id, long server, String status, String name, String distribution, String slug, boolean aPublic, String createdAt, String minDiskSize, String comment, String time, String priceHourly, long action, String system) {
        mId = id;
        mServer = server;
        mStatus = status;
        mName = name;
        mDistribution = distribution;
        mSlug = slug;
        mPublic = aPublic;
        mCreatedAt = createdAt;
        mMinDiskSize = minDiskSize;
        mComment = comment;
        mTime = time;
        mPriceHourly = priceHourly;
        mAction = action;
        mSystem = system;
    }

    public long getId() {
        return mId;
    }

    public long getServer() {
        return mServer;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getName() {
        return mName;
    }

    public String getDistribution() {
        return mDistribution;
    }

    public String getSlug() {
        return mSlug;
    }

    public boolean isPublic() {
        return mPublic;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public String getMinDiskSize() {
        return mMinDiskSize;
    }

    public String getComment() {
        return mComment;
    }

    public String getTime() {
        return mTime;
    }

    public String getPriceHourly() {
        return mPriceHourly;
    }

    public long getAction() {
        return mAction;
    }

    public String getSystem() {
        return mSystem;
    }
}
