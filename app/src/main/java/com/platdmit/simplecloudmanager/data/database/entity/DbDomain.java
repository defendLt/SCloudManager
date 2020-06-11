package com.platdmit.simplecloudmanager.data.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DbDomain {

    @PrimaryKey
    private long mId;
    private String mName;
    private String mType;
    private String mDeleteDate;

    private boolean mIsDelegate;

    public DbDomain(long id, String name, String type, String deleteDate, boolean isDelegate) {
        mId = id;
        mName = name;
        mType = type;
        mDeleteDate = deleteDate;
        mIsDelegate = isDelegate;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDeleteDate() {
        return mDeleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        mDeleteDate = deleteDate;
    }

    public boolean getIsDelegate() {
        return mIsDelegate;
    }

    public void setIsDelegate(boolean isDelegate) {
        mIsDelegate = isDelegate;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

//    public List<DbDomainRecord> getRecords() {
//        return records;
//    }
//
//    public void setRecords(List<DbDomainRecord> records) {
//        this.records = records;
//    }
}
