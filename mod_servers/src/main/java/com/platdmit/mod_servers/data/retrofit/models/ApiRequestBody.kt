package com.platdmit.mod_servers.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiRequestBody(
        @SerializedName("vps")
        @Expose
        private var servers: List<ApiServer>?,

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
        val serverStatistics: List<ApiStatistic>?,

        @SerializedName("backups")
        @Expose
        val serverBackups: List<ApiBackup>?
)