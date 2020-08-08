package com.platdmit.feature_servers.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiAverageCPU(
        @SerializedName("total")
        @Expose
        val total: String,

        @SerializedName("now")
        @Expose
        val now: String,

        @SerializedName("hourly")
        @Expose
        val hourly: Double
)