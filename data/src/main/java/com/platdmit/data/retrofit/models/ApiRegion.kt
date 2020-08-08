package com.platdmit.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiRegion(
        @SerializedName("id")
        @Expose
        val id: String,

        @SerializedName("slug")
        @Expose
        val slug: String,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("available")
        @Expose
        val available: String,

        @SerializedName("priority")
        @Expose
        val priority: String
)