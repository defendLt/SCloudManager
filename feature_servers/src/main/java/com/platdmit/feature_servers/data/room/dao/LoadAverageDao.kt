package com.platdmit.feature_servers.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.feature_servers.data.room.entity.DbLoadAverage

@Dao
interface LoadAverageDao : _root_ide_package_.com.platdmit.feature_servers.data.room.dao.BaseDao<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage> {
    @Query("SELECT * FROM dbloadaverage WHERE server = :serverId")
    fun getLoadAverageForServer(serverId: Long): List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage>?
}