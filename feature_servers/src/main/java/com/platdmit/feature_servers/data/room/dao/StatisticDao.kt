package com.platdmit.feature_servers.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.feature_servers.data.room.entity.DbStatistic

@Dao
interface StatisticDao : _root_ide_package_.com.platdmit.feature_servers.data.room.dao.BaseDao<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbStatistic> {
    @Query("SELECT * FROM dbstatistic WHERE server = :serverId")
    fun getStatisticsForServer(serverId: Long): List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbStatistic>?

    @Query("DELETE FROM dbstatistic WHERE server = :serverId")
    fun deleteAll(serverId: Long)
}