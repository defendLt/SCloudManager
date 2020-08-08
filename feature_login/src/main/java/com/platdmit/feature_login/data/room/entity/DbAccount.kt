package com.platdmit.feature_login.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbAccount(
        @PrimaryKey var id: Long,
        val isSubAccount: Boolean,
        val login: String,
        val email: String,
        var pass: String,
        var balance: Double,
        val vpsLimit: Int) {
    var isMain: Boolean = false
    var apiKey: String? = null
    var pin: String? = null
    var notificationCount = 0
}