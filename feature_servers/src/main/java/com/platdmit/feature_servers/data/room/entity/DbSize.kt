package com.platdmit.feature_servers.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbSize(
        @PrimaryKey val id: Long,
        val slug: String,
        val memory: String,
        val vcpus: String,
        val disk: String,
        val transfer: String,
        val priceMonthly: String,
        val priceHourly: String,
        val linked: String,
        val main: String,
        val isTest: Boolean,
        val isArchive: Boolean
)