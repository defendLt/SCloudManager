package com.platdmit.simplecloudmanager.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = DbDomain.class, parentColumns = "mId", childColumns = "mParentDomain"))
public class DbDomainRecord {

    @PrimaryKey
    private long mId;
    private String mPriority;
    private String mPort;
    private String mWeight;
    private String mTtl;

    private String mType;
    private String mName;
    private String mData;

    @ColumnInfo(index = true)
    private long mParentDomain;

    public DbDomainRecord(long id, String priority, String port, String weight, String ttl, String type, String name, String data, long parentDomain) {
        mId = id;
        mPriority = priority;
        mPort = port;
        mWeight = weight;
        mTtl = ttl;
        mType = type;
        mName = name;
        mData = data;
        mParentDomain = parentDomain;
    }

    public long getId() {
        return mId;
    }

    public String getPriority() {
        return mPriority;
    }

    public String getPort() {
        return mPort;
    }

    public String getWeight() {
        return mWeight;
    }

    public String getTtl() {
        return mTtl;
    }

    public String getType() {
        return mType;
    }

    public String getName() {
        return mName;
    }

    public String getData() {
        return mData;
    }

    public long getParentDomain() {
        return mParentDomain;
    }
}
