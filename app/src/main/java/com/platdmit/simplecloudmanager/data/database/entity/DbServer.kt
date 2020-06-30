package com.platdmit.simplecloudmanager.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbServer(
        @PrimaryKey val id: Long,
        val name: String,
        val memory: Int,
        val vcpus: Int,
        val disk: Int,
        val region: String,
        val image: Long,
        val backupPriceHourly: Double,
        val paymentDate: String,
        val paymentAmount: String,
        val paymentPeriod: String,
        val totalHours: String,
        val workedHours: String,
        val isLocked: Boolean,
        val status: String,
        val createdAt: String,
        val startedFirstAt: String,
        val startedAt: String,
        val isInstall: Boolean,
        val isError: Boolean,
        val password: String,
        val v4Ip: String,
        val isMbit200: Boolean
)