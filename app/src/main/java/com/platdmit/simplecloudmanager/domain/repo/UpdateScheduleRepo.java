package com.platdmit.simplecloudmanager.domain.repo;

import com.platdmit.simplecloudmanager.data.database.entity.DbUpdateSchedule;

import java.util.List;

public interface UpdateScheduleRepo {
    List<DbUpdateSchedule> getUpdateTimeList();
    long getUpdateTime(String key);
    void setUpdateTime(String key, long nextUpdate);
}
