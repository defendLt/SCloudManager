package com.platdmit.mod_servers.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbImage(
        @PrimaryKey val mid: Long,
        val name: String,
        val distribution: String,
        val osType: String,
        val minDiskSize: Int,
        val minMemorySize: Int,
        val rating: Int
)