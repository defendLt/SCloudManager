package com.platdmit.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.data.room.entity.DbUpdateSchedule

@Dao
interface UpdateScheduleDao : BaseDao<DbUpdateSchedule> {
    @Query("SELECT * FROM dbupdateschedule")
    fun getScheduleAll(): List<DbUpdateSchedule>?

    @Query("SELECT * FROM dbupdateschedule WHERE name = :name")
    fun getScheduleForName(name: String): DbUpdateSchedule?

    @Query("SELECT * FROM dbupdateschedule WHERE nextUpdate <= :data")
    fun getScheduleForUpdate(data: Long): List<DbUpdateSchedule>?
}