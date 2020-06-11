package com.platdmit.simplecloudmanager.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiDomain {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ttl")
    @Expose
    private String ttl;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("master")
    @Expose
    private Object master;
    @SerializedName("is_delegated")
    @Expose
    private boolean isDelegated;
    @SerializedName("delete_date")
    @Expose
    private String deleteDate;
    @SerializedName("records")
    @Expose
    private int records;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getMaster() {
        return master;
    }

    public void setMaster(Object master) {
        this.master = master;
    }

    public boolean getIsDelegated() {
        return isDelegated;
    }

    public void setIsDelegated(boolean delegated) {
        isDelegated = delegated;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }
}
