package com.platdmit.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiServer(
        @SerializedName("id")
        @Expose
        val id: Int,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("memory")
        @Expose
        val memory: Int,

        @SerializedName("vcpus")
        @Expose
        val vcpus: Int,

        @SerializedName("disk")
        @Expose
        val disk: Int,

        @SerializedName("region")
        @Expose
        val region: ApiRegion,

        @SerializedName("image")
        @Expose
        val image: ApiImage,

        @SerializedName("backup_price_hourly")
        @Expose
        val backupPriceHourly: Double,

        @SerializedName("size_slug")
        @Expose
        val sizeSlug: String,

        @SerializedName("locked")
        @Expose
        val locked: Boolean,

        @SerializedName("status")
        @Expose
        val status: String,

        @SerializedName("networks")
        @Expose
        val networks: ApiNetworks,

        @SerializedName("created_at")
        @Expose
        val createdAt: String,

        @SerializedName("started_first_at")
        @Expose
        val startedFirstAt: String,

        @SerializedName("started_at")
        @Expose
        val startedAt: String,

        @SerializedName("is_install")
        @Expose
        val isInstall: Boolean,

        @SerializedName("is_error")
        @Expose
        val isError: Boolean,

        @SerializedName("password")
        @Expose
        val password: String,

        @SerializedName("mbit200")
        @Expose
        val mbit200: Boolean,

        @SerializedName("billing")
        @Expose
        val billing: ApiBilling,

        @SerializedName("is_auction")
        @Expose
        val isAuction: Boolean
)