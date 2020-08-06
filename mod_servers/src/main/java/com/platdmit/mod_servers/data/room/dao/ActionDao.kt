package com.platdmit.mod_servers.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.mod_servers.data.room.entity.DbAction

@Dao
interface ActionDao : BaseDao<DbAction> {
    @Query("SELECT * FROM dbaction WHERE resourceId = :serverId")
    fun getActionForServer(serverId: Long): List<DbAction>?
}