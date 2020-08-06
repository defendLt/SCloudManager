package com.platdmit.mod_servers.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiAction(
        @SerializedName("id")
        @Expose
        val id: String,

        @SerializedName("status")
        @Expose
        val status: String,

        @SerializedName("type")
        @Expose
        val type: String,

        @SerializedName("started_at")
        @Expose
        val startedAt: String,

        @SerializedName("completed_at")
        @Expose
        val completedAt: String,

        @SerializedName("resource_id")
        @Expose
        val resourceId: String,

        @SerializedName("resource_type")
        @Expose
        val resourceType: String,

        @SerializedName("initiator")
        @Expose
        val initiator: String,

        @SerializedName("region_slug")
        @Expose
        val regionSlug: String
)