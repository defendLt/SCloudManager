package com.platdmit.simplecloudmanager.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.simplecloudmanager.data.database.entity.DbBackup

@Dao
interface BackupDao : BaseDao<DbBackup> {
    @Query("SELECT * FROM dbbackup WHERE server = :serverId")
    fun getBackupsForServer(serverId: Long): List<DbBackup>?
}