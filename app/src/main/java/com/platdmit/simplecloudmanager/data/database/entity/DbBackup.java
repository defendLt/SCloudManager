package com.platdmit.simplecloudmanager.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = DbServer.class, parentColumns = "mId", childColumns = "mServer"))
public class DbBackup {
    @PrimaryKey
    private long mId;
    @ColumnInfo(index = true)
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

    public DbBackup(){}

    public DbBackup(long id, long server, String status, String name, String distribution, String slug, boolean aPublic, String createdAt, String minDiskSize, String comment, String time, String priceHourly, long action, String system) {
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

    public void setId(long id) {
        mId = id;
    }

    public void setServer(long server) {
        mServer = server;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDistribution(String distribution) {
        mDistribution = distribution;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public void setPublic(boolean aPublic) {
        mPublic = aPublic;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public void setMinDiskSize(String minDiskSize) {
        mMinDiskSize = minDiskSize;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public void setPriceHourly(String priceHourly) {
        mPriceHourly = priceHourly;
    }

    public void setAction(long action) {
        mAction = action;
    }

    public void setSystem(String system) {
        mSystem = system;
    }
}
