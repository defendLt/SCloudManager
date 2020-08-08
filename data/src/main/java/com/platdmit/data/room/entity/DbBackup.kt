package com.platdmit.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = DbServer::class, parentColumns = ["id"], childColumns = ["server"])])
data class DbBackup(
        @PrimaryKey var id: Long,
        @ColumnInfo(index = true) var server: Long,
        var status: String,
        var name: String,
        var distribution: String,
        var slug: String,
        var isPublic: Boolean,
        var createdAt: String,
        var minDiskSize: String,
        var comment: String,
        var time: String,
        var priceHourly: String,
        var action: Long,
        var system: String
)