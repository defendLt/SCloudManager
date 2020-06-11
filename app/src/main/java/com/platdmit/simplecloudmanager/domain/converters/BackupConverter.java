package com.platdmit.simplecloudmanager.domain.converters;

import com.platdmit.simplecloudmanager.data.api.models.ApiBackup;
import com.platdmit.simplecloudmanager.data.database.entity.DbBackup;
import com.platdmit.simplecloudmanager.domain.models.Backup;

import java.util.List;

public interface BackupConverter {
    DbBackup fromApiToDb(ApiBackup apiBackup, long serverId);
    Backup fromDbToDomain(DbBackup dbBackup);
    List<Backup> fromDbToDomainList(List<DbBackup> dbList);
    List<DbBackup> fromApiToDbList(List<ApiBackup> apiList, long serverId);
}
