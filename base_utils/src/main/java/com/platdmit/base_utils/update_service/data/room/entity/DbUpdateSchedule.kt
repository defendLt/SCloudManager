package com.platdmit.base_utils.update_service.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DbUpdateSchedule(@PrimaryKey val name: String, val nextUpdate: Long) {
    var updateInterval: Long = 0
    var lastUpdate: Long = 0
}