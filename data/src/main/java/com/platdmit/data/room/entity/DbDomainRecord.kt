package com.platdmit.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = DbDomain::class, parentColumns = ["id"], childColumns = ["parentDomain"])])
data class DbDomainRecord(
        @PrimaryKey val id: Long,
        val priority: String,
        val port: String,
        val weight: String,
        val ttl: String,
        val type: String,
        val name: String,
        val data: String,
        @ColumnInfo(index = true) val parentDomain: Long
)