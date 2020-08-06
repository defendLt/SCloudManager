package com.platdmit.mod_servers.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = DbServer::class, parentColumns = ["id"], childColumns = ["server"])])
data class DbStatistic(
        @ColumnInfo(index = true) val server: Long,
        val time: String,
        val ramVal: Float,
        val cpuVal: Float
) {
    @PrimaryKey
    var tag: String = server.toString() + time
}