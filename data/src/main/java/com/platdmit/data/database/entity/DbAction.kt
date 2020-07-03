package com.platdmit.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = DbServer::class, parentColumns = ["id"], childColumns = ["resourceId"])])
data class DbAction(
        @PrimaryKey val id: Long,
        val status: String,
        val type: String,
        val startedAt: String,
        val completedAt: String,
        val resourceType: String,
        val initiator: String,
        val regionSlug: String,
        @ColumnInfo(index = true) val resourceId: Long
)