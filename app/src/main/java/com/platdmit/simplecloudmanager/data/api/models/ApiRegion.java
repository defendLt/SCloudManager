package com.platdmit.simplecloudmanager.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiRegion {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("available")
    @Expose
    private String available;
    @SerializedName("priority")
    @Expose
    private String priority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
