package com.platdmit.feature_servers.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.platdmit.feature_servers.data.room.entity.DbBackup

@Dao
interface BackupDao : _root_ide_package_.com.platdmit.feature_servers.data.room.dao.BaseDao<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbBackup> {
    @Query("SELECT * FROM dbbackup WHERE server = :serverId")
    fun getBackupsForServer(serverId: Long): List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbBackup>?
}