package com.platdmit.simplecloudmanager.domain.converters.implement;

import com.platdmit.simplecloudmanager.data.api.models.ApiDomainRecord;
import com.platdmit.simplecloudmanager.data.database.entity.DbDomainRecord;
import com.platdmit.simplecloudmanager.domain.converters.DomainRecordConverter;
import com.platdmit.simplecloudmanager.domain.models.DomainRecord;

import java.util.ArrayList;
import java.util.List;

public class DomainRecordConvertImp implements DomainRecordConverter {

    @Override
    public DbDomainRecord fromApiToDb(ApiDomainRecord apiDomainRecord, long domainId) {
        return new DbDomainRecord(
                apiDomainRecord.getId(),
                String.valueOf(apiDomainRecord.getPriority()),
                String.valueOf(apiDomainRecord.getPort()),
                String.valueOf(apiDomainRecord.getWeight()),
                String.valueOf(apiDomainRecord.getTtl()),
                apiDomainRecord.getType(),
                apiDomainRecord.getName(),
                apiDomainRecord.getData(),
                domainId
        );
    }

    @Override
    public DomainRecord fromDbToDomain(DbDomainRecord dbDomainRecord) {
        return new DomainRecord(
                dbDomainRecord.getId(),
                dbDomainRecord.getPriority(),
                dbDomainRecord.getPort(),
                dbDomainRecord.getWeight(),
                dbDomainRecord.getTtl(),
                dbDomainRecord.getType(),
                dbDomainRecord.getName(),
                dbDomainRecord.getData()
        );
    }

    @Override
    public List<DomainRecord> fromDbToDomainList(List<DbDomainRecord> dbList) {
        List<DomainRecord> convertList = new ArrayList<>();
        for (DbDomainRecord dbDomainRecord : dbList) {
            convertList.add(fromDbToDomain(dbDomainRecord));
        }
        return convertList;
    }

    @Override
    public List<DbDomainRecord> fromApiToDbList(List<ApiDomainRecord> apiList, long domainId) {
        List<DbDomainRecord> convertList = new ArrayList<>();
        for (ApiDomainRecord apiDomainRecord : apiList) {
            convertList.add(fromApiToDb(apiDomainRecord, domainId));
        }
        return convertList;
    }
}
