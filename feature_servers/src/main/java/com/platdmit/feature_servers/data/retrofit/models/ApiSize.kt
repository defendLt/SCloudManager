package com.platdmit.feature_servers.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiSize(
        @SerializedName("slug")
        @Expose
        val slug: String,

        @SerializedName("id")
        @Expose
        val id: String,

        @SerializedName("memory")
        @Expose
        val memory: String,

        @SerializedName("vcpus")
        @Expose
        val vcpus: String,

        @SerializedName("disk")
        @Expose
        val disk: String,

        @SerializedName("transfer")
        @Expose
        val transfer: String,

        @SerializedName("price_monthly")
        @Expose
        val priceMonthly: String,

        @SerializedName("price_hourly")
        @Expose
        val priceHourly: String,

        @SerializedName("linked")
        @Expose
        val linked: String,

        @SerializedName("main")
        @Expose
        val main: String,

        @SerializedName("test")
        @Expose
        val test: Boolean,

        @SerializedName("archive")
        @Expose
        val archive: Boolean,

        @SerializedName("regions")
        @Expose
        val regions: List<String>
)