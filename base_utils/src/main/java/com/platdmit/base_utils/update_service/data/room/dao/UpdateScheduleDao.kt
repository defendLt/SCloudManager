package com.platdmit.base_utils.update_service.data.room.dao

import androidx.room.*
import com.platdmit.base_utils.update_service.data.room.entity.DbUpdateSchedule

@Dao
interface UpdateScheduleDao {
    @Query("SELECT * FROM dbupdateschedule")
    fun getScheduleAll(): List<DbUpdateSchedule>?

    @Query("SELECT * FROM dbupdateschedule WHERE name = :name")
    fun getScheduleForName(name: String): DbUpdateSchedule?

    @Query("SELECT * FROM dbupdateschedule WHERE nextUpdate <= :data")
    fun getScheduleForUpdate(data: Long): List<DbUpdateSchedule>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dbElement: DbUpdateSchedule)

    @Update
    fun update(dbElement: DbUpdateSchedule)

    @Delete
    fun delete(dbElement: DbUpdateSchedule)
}