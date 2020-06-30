package com.platdmit.simplecloudmanager.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DbUpdateSchedule(@PrimaryKey val name: String, val nextUpdate: Long) {
    var updateInterval: Long = 0
    var lastUpdate: Long = 0
}