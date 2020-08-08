package com.platdmit.feature_domains.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiDomain(
        @SerializedName("id")
        @Expose
        val id: Long,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("ttl")
        @Expose
        val ttl: String,

        @SerializedName("type")
        @Expose
        val type: String,

        @SerializedName("master")
        @Expose
        val master: Any,

        @SerializedName("is_delegated")
        @Expose
        val isDelegated: Boolean,

        @SerializedName("delete_date")
        @Expose
        val deleteDate: String,

        @SerializedName("records")
        @Expose
        val records: Int
)