package com.platdmit.simplecloudmanager.data.database.dao;

import com.platdmit.simplecloudmanager.data.database.entity.DbBackup;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface BackupDao extends BaseDao<DbBackup>{
    @Query("SELECT * FROM dbbackup WHERE mServer = :serverId")
    List<DbBackup> getBackupsForServer(long serverId);
}
