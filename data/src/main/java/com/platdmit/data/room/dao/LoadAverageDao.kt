package com.platdmit.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.data.room.entity.DbLoadAverage

@Dao
interface LoadAverageDao : BaseDao<DbLoadAverage> {
    @Query("SELECT * FROM dbloadaverage WHERE server = :serverId")
    fun getLoadAverageForServer(serverId: Long): List<DbLoadAverage>?
}