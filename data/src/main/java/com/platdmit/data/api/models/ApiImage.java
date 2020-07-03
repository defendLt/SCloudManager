package com.platdmit.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiImage {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("distribution")
    @Expose
    private String distribution;
    @SerializedName("min_disk_size")
    @Expose
    private int minDiskSize;
    @SerializedName("min_memory_size")
    @Expose
    private int minMemorySize;
    @SerializedName("os_type")
    @Expose
    private String os_type;
    @SerializedName("rating")
    @Expose
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getMinDiskSize() {
        return minDiskSize;
    }

    public void setMinDiskSize(int minDiskSize) {
        this.minDiskSize = minDiskSize;
    }

    public int getMinMemorySize() {
        return minMemorySize;
    }

    public void setMinMemorySize(int minMemorySize) {
        this.minMemorySize = minMemorySize;
    }

    public String getOs_type() {
        return os_type;
    }

    public void setOs_type(String os_type) {
        this.os_type = os_type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
