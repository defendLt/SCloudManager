package com.platdmit.simplecloudmanager.data.database.dao;

import com.platdmit.simplecloudmanager.data.database.entity.DbLoadAverage;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface LoadAverageDao extends BaseDao<DbLoadAverage>{
    @Query("SELECT * FROM dbloadaverage WHERE mServer = :serverId")
    List<DbLoadAverage> getLoadAverageForServer(long serverId);
}
