package com.platdmit.simplecloudmanager.domain.models;

import java.util.List;

public class Domain {
    private long mId;

    private String mName;
    private String mType;
    private String mDeleteDate;
    private boolean mIsDelegate;

    private List<DomainRecord> mDomainRecords;

    public Domain(String name, String type) {
        mName = name;
        mType = type;
    }

    public Domain(long id, String name, String type, String deleteDate, Boolean isDelegate) {
        mId = id;
        mName = name;
        mType = type;
        mDeleteDate = deleteDate;
        mIsDelegate = isDelegate;
    }

    public long getId() {
        return mId;
    }

    public void setId(int id) {
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

    public Boolean getIsDelegate() {
        return mIsDelegate;
    }

    public void setIsDelegate(Boolean isDelegate) {
        mIsDelegate = isDelegate;
    }

    public List<DomainRecord> getDomainRecords() {
        return mDomainRecords;
    }

    public void setDomainRecords(List<DomainRecord> domainRecords) {
        mDomainRecords = domainRecords;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
