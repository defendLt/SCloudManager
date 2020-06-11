package com.platdmit.simplecloudmanager.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiRequestBody {
    @SerializedName("account")
    @Expose
    private ApiAccount account = null;

    @SerializedName("domain")
    @Expose
    private ApiDomain domain = null;

    @SerializedName("domains")
    @Expose
    private List<ApiDomain> domains = null;

    @SerializedName("domain_records")
    @Expose
    private List<ApiDomainRecord> domainRecords = null;

    @SerializedName("domain_record")
    @Expose
    private ApiDomainRecord domainRecord = null;

    @SerializedName("vps")
    @Expose
    private List<ApiServer> servers = null;

    @SerializedName("images")
    @Expose
    private List<ApiImage> images = null;

    @SerializedName("image")
    @Expose
    private ApiImage image = null;

    @SerializedName("loadAverage")
    @Expose
    private ApiLoadAverage loadAverage = null;

    @SerializedName("sizes")
    @Expose
    private List<ApiSize> sizes = null;

    @SerializedName("actions")
    @Expose
    private List<ApiAction> actions = null;

    @SerializedName("measures")
    @Expose
    private List<ApiStatistic> serverStatistics = null;

    @SerializedName("backups")
    @Expose
    private List<ApiBackup> serverBackups = null;


    public List<ApiDomain> getDomains() {
        return domains;
    }
    public List<ApiDomainRecord> getDomainRecords() {
        return domainRecords;
    }
    public List<ApiServer> getServers() {
        return servers;
    }
    public List<ApiImage> getImages() {
        return images;
    }
    public List<ApiSize> getSizes() {
        return sizes;
    }
    public List<ApiAction> getServerActions() {
        return actions;
    }
    public List<ApiStatistic> getServerStatistics() {
        return serverStatistics;
    }
    public List<ApiBackup> getServerBackups() {
        return serverBackups;
    }

    public ApiDomain getDomain() {
        return domain;
    }
    public ApiImage getImage() {
        return image;
    }
    public ApiAccount getAccount() {
        return account;
    }
    public ApiDomainRecord getDomainRecord() {
        return domainRecord;
    }
    public ApiLoadAverage getServerLoadAverage() {
        return loadAverage;
    }
}
