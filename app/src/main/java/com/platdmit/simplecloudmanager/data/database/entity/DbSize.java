package com.platdmit.simplecloudmanager.data.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DbSize {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String netId;
    private String slug;
    private String memory;
    private String vcpus;
    private String disk;
    private String transfer;
    private String priceMonthly;
    private String priceHourly;
    private String linked;
    private String main;

    private boolean test;
    private boolean archive;

    public DbSize(String netId, String slug, String memory, String vcpus, String disk, String transfer, String priceMonthly, String priceHourly, String linked, String main, boolean test, boolean archive) {
        this.netId = netId;
        this.slug = slug;
        this.memory = memory;
        this.vcpus = vcpus;
        this.disk = disk;
        this.transfer = transfer;
        this.priceMonthly = priceMonthly;
        this.priceHourly = priceHourly;
        this.linked = linked;
        this.main = main;
        this.test = test;
        this.archive = archive;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
