package com.platdmit.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiNetworks(
        @SerializedName("v4")
        @Expose
        private val _v4: MutableList<ApiV4>
) {
        val v4: ApiV4?
                get() = _v4.firstOrNull()
}