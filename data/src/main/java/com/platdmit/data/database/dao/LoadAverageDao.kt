package com.platdmit.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.data.database.entity.DbLoadAverage

@Dao
interface LoadAverageDao : BaseDao<DbLoadAverage> {
    @Query("SELECT * FROM dbloadaverage WHERE server = :serverId")
    fun getLoadAverageForServer(serverId: Long): List<DbLoadAverage>?
}