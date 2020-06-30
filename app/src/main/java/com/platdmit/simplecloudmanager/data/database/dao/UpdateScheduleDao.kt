package com.platdmit.simplecloudmanager.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.simplecloudmanager.data.database.entity.DbUpdateSchedule

@Dao
interface UpdateScheduleDao : BaseDao<DbUpdateSchedule> {
    @Query("SELECT * FROM dbupdateschedule")
    fun getScheduleAll(): List<DbUpdateSchedule>?

    @Query("SELECT * FROM dbupdateschedule WHERE name = :name")
    fun getScheduleForName(name: String): DbUpdateSchedule?

    @Query("SELECT * FROM dbupdateschedule WHERE nextUpdate <= :data")
    fun getScheduleForUpdate(data: Long): List<DbUpdateSchedule>?
}