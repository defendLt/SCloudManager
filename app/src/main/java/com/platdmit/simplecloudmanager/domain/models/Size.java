package com.platdmit.simplecloudmanager.domain.models;

public class Size {
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

    public Size(int id, String netId, String slug, String memory, String vcpus, String disk, String transfer, String priceMonthly, String priceHourly, String linked, String main, boolean test, boolean archive) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public String getNetId() {
        return netId;
    }

    public String getSlug() {
        return slug;
    }

    public String getMemory() {
        return memory;
    }

    public String getVcpus() {
        return vcpus;
    }

    public String getDisk() {
        return disk;
    }

    public String getTransfer() {
        return transfer;
    }

    public String getPriceMonthly() {
        return priceMonthly;
    }

    public String getPriceHourly() {
        return priceHourly;
    }

    public String getLinked() {
        return linked;
    }

    public String getMain() {
        return main;
    }

    public boolean isTest() {
        return test;
    }

    public boolean isArchive() {
        return archive;
    }
}
