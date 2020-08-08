package com.platdmit.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiV4 (
        @SerializedName("ip_address")
        @Expose

        val ipAddress: String,
        @SerializedName("netmask")
        @Expose

        val netmask: String,
        @SerializedName("gateway")
        @Expose
        val gateway: String
)