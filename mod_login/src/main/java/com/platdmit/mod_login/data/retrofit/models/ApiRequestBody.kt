package com.platdmit.mod_login.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiRequestBody (
        @SerializedName("account")
        @Expose
        val account: ApiAccount
)