package com.platdmit.feature_login.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiAccount (
        @SerializedName("uuid")
        @Expose
        val uuid : Int,
        @SerializedName("login")
        @Expose
        val login : String,
        @SerializedName("email")
        @Expose
        val email : String,
        @SerializedName("email_verified")
        @Expose
        val emailVerified : Boolean,
        @SerializedName("is_legal")
        @Expose
        val isLegal : Boolean,
        @SerializedName("is_resident")
        @Expose
        val isResident : Boolean,
        @SerializedName("balance")
        @Expose
        val balance : Double,
        @SerializedName("mailing")
        @Expose
        val mailing : Boolean,
        @SerializedName("dfa")
        @Expose
        val dfa : Boolean,
        @SerializedName("onboarding")
        @Expose
        val onboarding : Boolean,
        @SerializedName("subaccounts_enabled")
        @Expose
        val subaccountsEnabled : Boolean,
        @SerializedName("vps_limit")
        @Expose
        val vpsLimit : Int,
        @SerializedName("is_subaccount")
        @Expose
        val isSubaccount : Boolean,
        @SerializedName("notifications")
        @Expose
        val notifications : String
)