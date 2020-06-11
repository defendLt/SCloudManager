package com.platdmit.simplecloudmanager.domain.models;

public class DomainRecord {
    private long mId;
    private String mPriority;
    private String mPort;
    private String mWeight;
    private String mTtl;

    private String mType;
    private String mName;
    private String mData;

    private int mParentDomain;

    public DomainRecord(long id, String priority, String port, String weight, String ttl, String type, String name, String data) {
        mId = id;
        mPriority = priority;
        mPort = port;
        mWeight = weight;
        mTtl = ttl;
        mType = type;
        mName = name;
        mData = data;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }

    public int getParentDomain() {
        return mParentDomain;
    }

    public void setParentDomain(int parentDomain) {
        mParentDomain = parentDomain;
    }

    public long getId() {
        return mId;
    }

    public String getPriority() {
        return mPriority;
    }

    public void setPriority(String priority) {
        mPriority = priority;
    }

    public String getPort() {
        return mPort;
    }

    public void setPort(String port) {
        mPort = port;
    }

    public String getWeight() {
        return mWeight;
    }

    public void setWeight(String weight) {
        mWeight = weight;
    }

    public String getTtl() {
        return mTtl;
    }

    public void setTtl(String ttl) {
        mTtl = ttl;
    }
}
