package com.platdmit.feature_servers.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.feature_servers.data.room.entity.DbSize

@Dao
interface SizeDao : _root_ide_package_.com.platdmit.feature_servers.data.room.dao.BaseDao<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbSize> {
    @Query("SELECT * FROM dbsize")
    fun getAllElements(): List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbSize>?

    @Query("SELECT * FROM dbsize WHERE id = :id")
    fun getElement(id: String): _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbSize?
}