package com.platdmit.data.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiBilling(
        @SerializedName("payment_date")
        @Expose
        val paymentDate: String,

        @SerializedName("payment_amount")
        @Expose
        val paymentAmount: String,

        @SerializedName("payment_period")
        @Expose
        val paymentPeriod: String,

        @SerializedName("price")
        @Expose
        val price: String,

        @SerializedName("payperiod")
        @Expose
        val payperiod: String,

        @SerializedName("total_hours")
        @Expose
        val totalHours: String,

        @SerializedName("worked_hours")
        @Expose
        val workedHours: String
)