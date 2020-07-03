package com.platdmit.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiServer {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("memory")
    @Expose
    private int memory;
    @SerializedName("vcpus")
    @Expose
    private int vcpus;
    @SerializedName("disk")
    @Expose
    private int disk;
    @SerializedName("region")
    @Expose
    private ApiRegion region;
    @SerializedName("image")
    @Expose
    private ApiImage image;
    @SerializedName("backup_price_hourly")
    @Expose
    private double backupPriceHourly;
    @SerializedName("size_slug")
    @Expose
    private String sizeSlug;
    @SerializedName("locked")
    @Expose
    private boolean locked;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("networks")
    @Expose
    private ApiNetworks networks;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("started_first_at")
    @Expose
    private String startedFirstAt;
    @SerializedName("started_at")
    @Expose
    private String startedAt;
    @SerializedName("is_install")
    @Expose
    private boolean isInstall;
    @SerializedName("is_error")
    @Expose
    private boolean isError;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("mbit200")
    @Expose
    private boolean mbit200;
    @SerializedName("billing")
    @Expose
    private ApiBilling billing;
    @SerializedName("is_auction")
    @Expose
    private boolean isAuction;

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

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getVcpus() {
        return vcpus;
    }

    public void setVcpus(int vcpus) {
        this.vcpus = vcpus;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public ApiRegion getRegion() {
        return region;
    }

    public void setRegion(ApiRegion region) {
        this.region = region;
    }

    public double getBackupPriceHourly() {
        return backupPriceHourly;
    }

    public void setBackupPriceHourly(double backupPriceHourly) {
        this.backupPriceHourly = backupPriceHourly;
    }

    public String getSizeSlug() {
        return sizeSlug;
    }

    public void setSizeSlug(String sizeSlug) {
        this.sizeSlug = sizeSlug;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ApiNetworks getNetworks() {
        return networks;
    }

    public void setNetworks(ApiNetworks networks) {
        this.networks = networks;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStartedFirstAt() {
        return startedFirstAt;
    }

    public void setStartedFirstAt(String startedFirstAt) {
        this.startedFirstAt = startedFirstAt;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public boolean isIsInstall() {
        return isInstall;
    }

    public void setIsInstall(boolean isInstall) {
        this.isInstall = isInstall;
    }

    public boolean isIsError() {
        return isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMbit200() {
        return mbit200;
    }

    public void setMbit200(boolean mbit200) {
        this.mbit200 = mbit200;
    }

    public ApiBilling getBilling() {
        return billing;
    }

    public void setBilling(ApiBilling billing) {
        this.billing = billing;
    }

    public boolean isIsAuction() {
        return isAuction;
    }

    public void setIsAuction(boolean isAuction) {
        this.isAuction = isAuction;
    }

    public ApiImage getImage() {
        return image;
    }

    public void setImage(ApiImage image) {
        this.image = image;
    }
}
