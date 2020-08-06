package com.platdmit.mod_login.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiAuth (
        @SerializedName("account")
        @Expose
        val requestBody: ApiRequestBody,
        @SerializedName("session_key")
        @Expose
        val sessionKey : String
)