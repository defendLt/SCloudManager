package com.platdmit.simplecloudmanager.domain.converters.implement;

import com.platdmit.simplecloudmanager.data.api.models.ApiBackup;
import com.platdmit.simplecloudmanager.data.database.entity.DbBackup;
import com.platdmit.simplecloudmanager.domain.converters.BackupConverter;
import com.platdmit.simplecloudmanager.domain.models.Backup;

import java.util.ArrayList;
import java.util.List;

public class BackupConvertImp implements BackupConverter {
    @Override
    public DbBackup fromApiToDb(ApiBackup apiBackup, long serverId) {
        return new DbBackup(
                Integer.parseInt(apiBackup.getId()),
                serverId,
                apiBackup.getStatus(),
                apiBackup.getName(),
                apiBackup.getDistribution(),
                apiBackup.getSlug(),
                apiBackup.is_public(),
                apiBackup.getCreatedAt(),
                apiBackup.getMinDiskSize(),
                apiBackup.getComment(),
                apiBackup.getTime(),
                apiBackup.getPriceHourly(),
                Integer.parseInt(apiBackup.getAction()),
                apiBackup.getSystem()
        );
    }

    @Override
    public Backup fromDbToDomain(DbBackup dbBackup) {
        return new Backup(
                dbBackup.getId(),
                dbBackup.getServer(),
                dbBackup.getStatus(),
                dbBackup.getName(),
                dbBackup.getDistribution(),
                dbBackup.getSlug(),
                dbBackup.isPublic(),
                dbBackup.getCreatedAt(),
                dbBackup.getMinDiskSize(),
                dbBackup.getComment(),
                dbBackup.getTime(),
                dbBackup.getPriceHourly(),
                dbBackup.getAction(),
                dbBackup.getSystem()
        );
    }

    @Override
    public List<Backup> fromDbToDomainList(List<DbBackup> dbList) {
        List<Backup> convertList = new ArrayList<>();
        for (DbBackup dbBackup : dbList) {
            convertList.add(fromDbToDomain(dbBackup));
        }
        return convertList;
    }

    @Override
    public List<DbBackup> fromApiToDbList(List<ApiBackup> apiList, long serverId) {
        List<DbBackup> convertList = new ArrayList<>();
        for (ApiBackup apiBackup : apiList) {
            convertList.add(fromApiToDb(apiBackup, serverId));
        }
        return convertList;
    }
}
