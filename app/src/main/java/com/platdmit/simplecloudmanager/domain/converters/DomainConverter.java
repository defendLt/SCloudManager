package com.platdmit.simplecloudmanager.domain.converters;

import com.platdmit.simplecloudmanager.data.api.models.ApiDomain;
import com.platdmit.simplecloudmanager.data.database.entity.DbDomain;
import com.platdmit.simplecloudmanager.domain.models.Domain;

import java.util.List;

public interface DomainConverter {
    DbDomain fromApiToDb(ApiDomain apiDomain);
    Domain fromDbToDomain(DbDomain dbDomain);
    Domain fromApiToDomain(ApiDomain apiDomain);
    List<Domain> fromApiToDomainList(List<ApiDomain> apiList);
    List<Domain> fromDbToDomainList(List<DbDomain> dbList);
    List<DbDomain> fromApiToDbList(List<ApiDomain> apiList);
}
