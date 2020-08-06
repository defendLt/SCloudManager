package com.platdmit.mod_servers.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiLoadAverage (
        @SerializedName("RAM")
        @Expose
        val rAM: ApiAverageRAM,
        @SerializedName("CPU")
        @Expose
        val cPU: ApiAverageCPU,
        @SerializedName("disk")
        @Expose
        val disk: ApiAverageDisk
)