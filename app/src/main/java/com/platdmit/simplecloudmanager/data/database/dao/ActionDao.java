package com.platdmit.simplecloudmanager.data.database.dao;

import com.platdmit.simplecloudmanager.data.database.entity.DbAction;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ActionDao extends BaseDao<DbAction> {
    @Query("SELECT * FROM dbaction WHERE mResourceId = :serverId")
    List<DbAction> getActionForServer(long serverId);
}
