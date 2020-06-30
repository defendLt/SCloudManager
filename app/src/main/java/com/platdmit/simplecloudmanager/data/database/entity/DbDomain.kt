package com.platdmit.simplecloudmanager.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbDomain(
        @PrimaryKey var id: Long,
        var name: String,
        var type: String,
        var deleteDate: String,
        var isDelegate: Boolean
)