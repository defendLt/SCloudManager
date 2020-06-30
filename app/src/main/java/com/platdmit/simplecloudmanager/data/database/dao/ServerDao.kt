package com.platdmit.simplecloudmanager.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.simplecloudmanager.data.database.entity.DbServer

@Dao
interface ServerDao : BaseDao<DbServer?> {
    @Query("SELECT * FROM dbserver")
    fun getAllElement(): List<DbServer>?

    @Query("SELECT * FROM dbserver WHERE id = :id")
    fun getElement(id: Long): DbServer?
}