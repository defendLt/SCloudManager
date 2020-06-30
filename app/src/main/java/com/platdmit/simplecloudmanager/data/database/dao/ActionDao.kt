package com.platdmit.simplecloudmanager.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.simplecloudmanager.data.database.entity.DbAction

@Dao
interface ActionDao : BaseDao<DbAction> {
    @Query("SELECT * FROM dbaction WHERE resourceId = :serverId")
    fun getActionForServer(serverId: Long): List<DbAction>?
}