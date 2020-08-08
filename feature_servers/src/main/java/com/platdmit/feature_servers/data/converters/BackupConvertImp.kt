package com.platdmit.feature_servers.data.converters

import com.platdmit.feature_servers.domain.converters.BackupConverter
import com.platdmit.feature_servers.domain.models.Backup
import com.platdmit.feature_servers.data.retrofit.models.ApiBackup
import com.platdmit.feature_servers.data.room.entity.DbBackup
import javax.inject.Inject

class BackupConvertImp
@Inject
constructor() : _root_ide_package_.com.platdmit.feature_servers.domain.converters.BackupConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup, _root_ide_package_.com.platdmit.feature_servers.domain.models.Backup, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbBackup> {
    override fun fromApiToDb(apiBackup: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup, serverId: Long): _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbBackup {
        return _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbBackup(apiBackup.id.toInt().toLong(),
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

    override fun fromDbToDomain(dbBackup: _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbBackup): _root_ide_package_.com.platdmit.feature_servers.domain.models.Backup {
        return _root_ide_package_.com.platdmit.feature_servers.domain.models.Backup(
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

    override fun fromDbToDomainList(dbList: List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbBackup>): List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Backup> = dbList.map { fromDbToDomain(it) }

    override fun fromApiToDbList(apiList: List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup>, serverId: Long): List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbBackup> = apiList.map { fromApiToDb(it, serverId) }
}