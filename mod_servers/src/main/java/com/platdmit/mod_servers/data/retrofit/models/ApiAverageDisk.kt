package com.platdmit.mod_servers.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiAverageDisk(
        @SerializedName("total")
        @Expose
        val total: String,

        @SerializedName("free")
        @Expose
        val free: String
)