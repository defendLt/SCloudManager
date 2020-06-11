package com.platdmit.simplecloudmanager.data.database.dao;

import com.platdmit.simplecloudmanager.data.database.entity.DbStatistic;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface StatisticDao extends BaseDao<DbStatistic>{
    @Query("SELECT * FROM dbstatistic WHERE mServer = :serverId")
    List<DbStatistic> getStatisticsForServer(long serverId);

    @Query("DELETE FROM dbstatistic WHERE mServer = :serverId")
    void deleteAll(long serverId);
}
