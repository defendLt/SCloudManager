package com.platdmit.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.data.room.entity.DbAction

@Dao
interface ActionDao : BaseDao<DbAction> {
    @Query("SELECT * FROM dbaction WHERE resourceId = :serverId")
    fun getActionForServer(serverId: Long): List<DbAction>?
}