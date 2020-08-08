package com.platdmit.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.data.room.entity.DbStatistic

@Dao
interface StatisticDao : BaseDao<DbStatistic> {
    @Query("SELECT * FROM dbstatistic WHERE server = :serverId")
    fun getStatisticsForServer(serverId: Long): List<DbStatistic>?

    @Query("DELETE FROM dbstatistic WHERE server = :serverId")
    fun deleteAll(serverId: Long)
}