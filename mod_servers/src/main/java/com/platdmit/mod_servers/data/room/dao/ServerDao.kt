package com.platdmit.mod_servers.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.mod_servers.data.room.entity.DbServer

@Dao
interface ServerDao : BaseDao<DbServer?> {
    @Query("SELECT * FROM dbserver")
    fun getAllElement(): List<DbServer>?

    @Query("SELECT * FROM dbserver WHERE id = :id")
    fun getElement(id: Long): DbServer?
}