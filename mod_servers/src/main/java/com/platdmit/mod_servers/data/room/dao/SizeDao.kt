package com.platdmit.mod_servers.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.mod_servers.data.room.entity.DbSize

@Dao
interface SizeDao : BaseDao<DbSize> {
    @Query("SELECT * FROM dbsize")
    fun getAllElements(): List<DbSize>?

    @Query("SELECT * FROM dbsize WHERE id = :id")
    fun getElement(id: String): DbSize?
}