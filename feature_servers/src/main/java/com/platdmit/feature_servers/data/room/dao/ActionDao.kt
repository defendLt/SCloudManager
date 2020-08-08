package com.platdmit.feature_servers.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.feature_servers.data.room.entity.DbAction

@Dao
interface ActionDao : _root_ide_package_.com.platdmit.feature_servers.data.room.dao.BaseDao<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbAction> {
    @Query("SELECT * FROM dbaction WHERE resourceId = :serverId")
    fun getActionForServer(serverId: Long): List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbAction>?
}