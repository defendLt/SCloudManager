package com.platdmit.simplecloudmanager.domain.converters

import com.platdmit.data.api.models.ApiBackup
import com.platdmit.data.database.entity.DbBackup
import com.platdmit.simplecloudmanager.domain.models.Backup

interface BackupConverter {
    fun fromApiToDb(apiBackup: ApiBackup, serverId: Long): DbBackup
    fun fromDbToDomain(dbBackup: DbBackup): Backup
    fun fromDbToDomainList(dbList: List<DbBackup>): List<Backup>
    fun fromApiToDbList(apiList: List<ApiBackup>, serverId: Long): List<DbBackup>
}