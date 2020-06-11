package com.platdmit.simplecloudmanager.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = DbServer.class, parentColumns = "mId", childColumns = "mResourceId"))
public class DbAction {
    @PrimaryKey
    private long mId;
    private String mStatus;
    private String mType;
    private String mStartedAt;
    private String mCompletedAt;
    private String mResourceType;
    private String mInitiator;
    private String mRegionSlug;
    @ColumnInfo(index = true)
    private long mResourceId;

    public DbAction(long id, String status, String type, String startedAt, String completedAt, String resourceType, String initiator, String regionSlug, long resourceId) {
        mId = id;
        mStatus = status;
        mType = type;
        mStartedAt = startedAt;
        mCompletedAt = completedAt;
        mResourceType = resourceType;
        mInitiator = initiator;
        mRegionSlug = regionSlug;
        mResourceId = resourceId;
    }

    public long getId() {
        return mId;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getType() {
        return mType;
    }

    public String getStartedAt() {
        return mStartedAt;
    }

    public String getCompletedAt() {
        return mCompletedAt;
    }

    public String getResourceType() {
        return mResourceType;
    }

    public String getInitiator() {
        return mInitiator;
    }

    public String getRegionSlug() {
        return mRegionSlug;
    }

    public long getResourceId() {
        return mResourceId;
    }
}
