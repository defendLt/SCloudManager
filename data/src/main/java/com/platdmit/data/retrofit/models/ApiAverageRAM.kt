package com.platdmit.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiAverageRAM(
        @SerializedName("total")
        @Expose
        val total: String,

        @SerializedName("stats")
        @Expose
        val stats: String,

        @SerializedName("now")
        @Expose
        val now: Double,

        @SerializedName("hourly")
        @Expose
        val hourly: Double
)