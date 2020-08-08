package com.platdmit.feature_servers.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiBackup(
        @SerializedName("id")
        @Expose
        val id: String,

        @SerializedName("status")
        @Expose
        val status: String,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("distribution")
        @Expose
        val distribution: String,

        @SerializedName("slug")
        @Expose
        val slug: String,

        @SerializedName("public")
        @Expose
        val _public: Boolean,

        @SerializedName("created_at")
        @Expose
        val createdAt: String,

        @SerializedName("min_disk_size")
        @Expose
        val minDiskSize: String,

        @SerializedName("comment")
        @Expose
        val comment: String,

        @SerializedName("days")
        @Expose
        val days: List<String>,

        @SerializedName("time")
        @Expose
        val time: String,

        @SerializedName("price_hourly")
        @Expose
        val priceHourly: String,

        @SerializedName("action")
        @Expose
        val action: String,

        @SerializedName("system")
        @Expose
        val system: String
)