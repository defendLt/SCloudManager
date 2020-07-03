package com.platdmit.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiBackup {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("distribution")
    @Expose
    private String distribution;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("public")
    @Expose
    private boolean _public;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("min_disk_size")
    @Expose
    private String minDiskSize;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("days")
    @Expose
    private List<String> days = null;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("price_hourly")
    @Expose
    private String priceHourly;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("system")
    @Expose
    private String system;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean is_public() {
        return _public;
    }

    public void set_public(boolean _public) {
        this._public = _public;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getMinDiskSize() {
        return minDiskSize;
    }

    public void setMinDiskSize(String minDiskSize) {
        this.minDiskSize = minDiskSize;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPriceHourly() {
        return priceHourly;
    }

    public void setPriceHourly(String priceHourly) {
        this.priceHourly = priceHourly;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
