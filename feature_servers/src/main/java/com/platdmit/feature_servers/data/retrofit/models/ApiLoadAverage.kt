package com.platdmit.feature_servers.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiLoadAverage (
        @SerializedName("RAM")
        @Expose
        val rAM: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAverageRAM,
        @SerializedName("CPU")
        @Expose
        val cPU: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAverageCPU,
        @SerializedName("disk")
        @Expose
        val disk: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAverageDisk
)