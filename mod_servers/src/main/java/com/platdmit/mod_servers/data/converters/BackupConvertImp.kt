package com.platdmit.mod_servers.data.converters

import com.platdmit.mod_servers.domain.converters.BackupConverter
import com.platdmit.mod_servers.domain.models.Backup
import com.platdmit.mod_servers.data.retrofit.models.ApiBackup
import com.platdmit.mod_servers.data.room.entity.DbBackup
import javax.inject.Inject

class BackupConvertImp
@Inject
constructor() : BackupConverter<ApiBackup, Backup, DbBackup> {
    override fun fromApiToDb(apiBackup: ApiBackup, serverId: Long): DbBackup {
        return DbBackup(apiBackup.id.toInt().toLong(),
                serverId,
                apiBackup.status,
                apiBackup.name,
                apiBackup.distribution,
                apiBackup.slug,
                apiBackup.is_public,
                apiBackup.createdAt,
                apiBackup.minDiskSize,
                apiBackup.comment,
                apiBackup.time,
                apiBackup.priceHourly, apiBackup.action.toInt().toLong(),
                apiBackup.system
        )
    }

    override fun fromDbToDomain(dbBackup: DbBackup): Backup {
        return Backup(
                dbBackup.id,
                dbBackup.server,
                dbBackup.status,
                dbBackup.name,
                dbBackup.distribution,
                dbBackup.slug,
                dbBackup.isPublic,
                dbBackup.createdAt,
                dbBackup.minDiskSize,
                dbBackup.comment,
                dbBackup.time,
                dbBackup.priceHourly,
                dbBackup.action,
                dbBackup.system
        )
    }

    override fun fromDbToDomainList(dbList: List<DbBackup>): List<Backup> = dbList.map { fromDbToDomain(it) }

    override fun fromApiToDbList(apiList: List<ApiBackup>, serverId: Long): List<DbBackup> = apiList.map { fromApiToDb(it, serverId) }
}