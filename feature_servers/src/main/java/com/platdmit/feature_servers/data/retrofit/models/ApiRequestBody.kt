package com.platdmit.feature_servers.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiRequestBody(
        @SerializedName("vps")
        @Expose
        private var servers: List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiServer>?,

        @SerializedName("images")
        @Expose
        val images: List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiImage>?,

        @SerializedName("image")
        @Expose
        val image: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiImage?,

        @SerializedName("loadAverage")
        @Expose
        val loadAverage: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage?,

        @SerializedName("sizes")
        @Expose
        val sizes: List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiSize>?,

        @SerializedName("actions")
        @Expose
        val actions: List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction>?,

        @SerializedName("measures")
        @Expose
        val serverStatistics: List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic>?,

        @SerializedName("backups")
        @Expose
        val serverBackups: List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup>?
)