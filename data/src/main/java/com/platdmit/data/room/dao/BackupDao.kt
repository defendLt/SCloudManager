package com.platdmit.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.data.room.entity.DbBackup

@Dao
interface BackupDao : BaseDao<DbBackup> {
    @Query("SELECT * FROM dbbackup WHERE server = :serverId")
    fun getBackupsForServer(serverId: Long): List<DbBackup>?
}