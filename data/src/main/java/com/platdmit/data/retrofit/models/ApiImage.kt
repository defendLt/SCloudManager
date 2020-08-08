package com.platdmit.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiImage(
        @SerializedName("id")
        @Expose
        val id: Int,
        
        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("distribution")
        @Expose
        val distribution: String,

        @SerializedName("min_disk_size")
        @Expose
        val minDiskSize: Int,

        @SerializedName("min_memory_size")
        @Expose
        val minMemorySize: Int,

        @SerializedName("os_type")
        @Expose
        val os_type: String,

        @SerializedName("rating")
        @Expose
        val rating: Int
)