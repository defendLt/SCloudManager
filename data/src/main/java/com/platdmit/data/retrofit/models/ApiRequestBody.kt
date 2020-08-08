package com.platdmit.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiRequestBody(
        @SerializedName("account")
        @Expose
        val account: ApiAccount,

        @SerializedName("domain")
        @Expose
        val domain: ApiDomain?,

        @SerializedName("domains")
        @Expose
        val domains: List<ApiDomain>?,

        @SerializedName("domain_record")
        @Expose
        val domainRecord: ApiDomainRecord?,

        @SerializedName("domain_records")
        @Expose
        val domainRecords: List<ApiDomainRecord>?,

        @SerializedName("vps")
        @Expose
        val servers: List<ApiServer>?,

        @SerializedName("images")
        @Expose
        val images: List<ApiImage>?,

        @SerializedName("image")
        @Expose
        val image: ApiImage?,

        @SerializedName("loadAverage")
        @Expose
        val loadAverage: ApiLoadAverage?,

        @SerializedName("sizes")
        @Expose
        val sizes: List<ApiSize>?,

        @SerializedName("actions")
        @Expose
        val actions: List<ApiAction>?,

        @SerializedName("measures")
        @Expose
        val statistics: List<ApiStatistic>?,

        @SerializedName("backups")
        @Expose
        val backups: List<ApiBackup>?
)