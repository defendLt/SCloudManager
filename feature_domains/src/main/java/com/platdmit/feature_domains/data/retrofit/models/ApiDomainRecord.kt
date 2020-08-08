package com.platdmit.feature_domains.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiDomainRecord(
        @SerializedName("id")
        @Expose
        val id: Long,

        @SerializedName("type")
        @Expose
        val type: String,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("data")
        @Expose
        val data: String,

        @SerializedName("priority")
        @Expose
        val priority: Int,

        @SerializedName("ttl")
        @Expose
        val ttl: Int,

        @SerializedName("port")
        @Expose
        val port: Int,

        @SerializedName("weight")
        @Expose
        val weight: Int
)