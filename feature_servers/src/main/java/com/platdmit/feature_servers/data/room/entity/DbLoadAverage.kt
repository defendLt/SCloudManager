package com.platdmit.feature_servers.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbServer::class, parentColumns = ["id"], childColumns = ["server"])])
data class DbLoadAverage(
        @PrimaryKey val name: String,
        @ColumnInfo(index = true) val server: Long,
        val total: String,
        val value: String,
        val percent: Double
)