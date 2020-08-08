package com.platdmit.feature_servers.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.feature_servers.data.room.entity.DbServer

@Dao
interface ServerDao : _root_ide_package_.com.platdmit.feature_servers.data.room.dao.BaseDao<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbServer?> {
    @Query("SELECT * FROM dbserver")
    fun getAllElement(): List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbServer>?

    @Query("SELECT * FROM dbserver WHERE id = :id")
    fun getElement(id: Long): _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbServer?
}