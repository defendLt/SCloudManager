package com.platdmit.simplecloudmanager.data.database.dao;

import com.platdmit.simplecloudmanager.data.database.entity.DbUpdateSchedule;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UpdateScheduleDao extends BaseDao<DbUpdateSchedule>{

    @Query("SELECT * FROM dbupdateschedule")
    List<DbUpdateSchedule> getScheduleAll();

    @Query("SELECT * FROM dbupdateschedule WHERE mName = :name")
    DbUpdateSchedule getScheduleForName(String name);

    @Query("SELECT * FROM dbupdateschedule WHERE mNextUpdate <= :data")
    List<DbUpdateSchedule> getScheduleForUpdate(Long data);
}
