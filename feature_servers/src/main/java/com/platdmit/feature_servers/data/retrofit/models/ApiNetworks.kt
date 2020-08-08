package com.platdmit.feature_servers.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiNetworks(
        @SerializedName("v4")
        @Expose
        private val _v4: MutableList<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiV4>
) {
        val v4: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiV4?
                get() = _v4.firstOrNull()
}