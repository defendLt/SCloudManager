package com.platdmit.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiStatistic(
        @SerializedName("time")
        @Expose
        val time: String,

        @SerializedName("RAM")
        @Expose
        val ram: String,

        @SerializedName("CPU")
        @Expose
        val cpu: String,

        @SerializedName("disk")
        @Expose
        val disk: ApiDisk,

        @SerializedName("traffic")
        @Expose
        val traffic: ApiTraffic
)

data class ApiDisk(
        @SerializedName("write")
        @Expose
        val write: String,

        @SerializedName("read")
        @Expose
        val read: String
)

data class ApiTraffic(
        @SerializedName("incoming")
        @Expose
        val incoming: String,

        @SerializedName("outgoing")
        @Expose
        val outgoing: String
)