package com.platdmit.simplecloudmanager.domain.converters;

import com.platdmit.simplecloudmanager.data.api.models.ApiDomainRecord;
import com.platdmit.simplecloudmanager.data.database.entity.DbDomainRecord;
import com.platdmit.simplecloudmanager.domain.models.DomainRecord;

import java.util.List;

public interface DomainRecordConverter {
    DbDomainRecord fromApiToDb(ApiDomainRecord apiDomainRecord, long domainId);
    DomainRecord fromDbToDomain(DbDomainRecord dbDomainRecord);
    List<DomainRecord> fromDbToDomainList(List<DbDomainRecord> dbList);
    List<DbDomainRecord> fromApiToDbList(List<ApiDomainRecord> apiList, long domainId);
}
