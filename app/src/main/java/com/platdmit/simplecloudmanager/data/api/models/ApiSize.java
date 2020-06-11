package com.platdmit.simplecloudmanager.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiSize {
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("memory")
    @Expose
    private String memory;
    @SerializedName("vcpus")
    @Expose
    private String vcpus;
    @SerializedName("disk")
    @Expose
    private String disk;
    @SerializedName("transfer")
    @Expose
    private String transfer;
    @SerializedName("price_monthly")
    @Expose
    private String priceMonthly;
    @SerializedName("price_hourly")
    @Expose
    private String priceHourly;
    @SerializedName("linked")
    @Expose
    private String linked;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("test")
    @Expose
    private boolean test;
    @SerializedName("archive")
    @Expose
    private boolean archive;
    @SerializedName("regions")
    @Expose
    private List<String> regions = null;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getVcpus() {
        return vcpus;
    }

    public void setVcpus(String vcpus) {
        this.vcpus = vcpus;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getPriceMonthly() {
        return priceMonthly;
    }

    public void setPriceMonthly(String priceMonthly) {
        this.priceMonthly = priceMonthly;
    }

    public String getPriceHourly() {
        return priceHourly;
    }

    public void setPriceHourly(String priceHourly) {
        this.priceHourly = priceHourly;
    }

    public String getLinked() {
        return linked;
    }

    public void setLinked(String linked) {
        this.linked = linked;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }
}
