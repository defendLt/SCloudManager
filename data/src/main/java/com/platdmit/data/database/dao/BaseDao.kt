package com.platdmit.data.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<Db> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dbElement: Db)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(dbElements: List<Db>)

    @Update
    fun update(dbElement: Db)

    @Update
    fun updateAll(dbElements: List<Db>)

    @Delete
    fun delete(dbElement: Db)
}