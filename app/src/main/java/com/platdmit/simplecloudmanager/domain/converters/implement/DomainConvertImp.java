package com.platdmit.simplecloudmanager.domain.converters.implement;

import com.platdmit.simplecloudmanager.data.api.models.ApiDomain;
import com.platdmit.simplecloudmanager.data.database.entity.DbDomain;
import com.platdmit.simplecloudmanager.domain.converters.DomainConverter;
import com.platdmit.simplecloudmanager.domain.models.Domain;

import java.util.ArrayList;
import java.util.List;

public class DomainConvertImp implements DomainConverter {

    @Override
    public DbDomain fromApiToDb(ApiDomain apiDomain) {
        return new DbDomain(apiDomain.getId(), apiDomain.getName(), apiDomain.getType(), apiDomain.getDeleteDate(), apiDomain.getIsDelegated());
    }

    @Override
    public Domain fromDbToDomain(DbDomain dbDomain) {
        return new Domain(dbDomain.getId(), dbDomain.getName(), dbDomain.getType(), dbDomain.getDeleteDate(), dbDomain.getIsDelegate());
    }

    @Override
    public Domain fromApiToDomain(ApiDomain apiDomain) {
        return new Domain(apiDomain.getId(), apiDomain.getName(), apiDomain.getType(), apiDomain.getDeleteDate(), apiDomain.getIsDelegated());
    }

    @Override
    public List<Domain> fromApiToDomainList(List<ApiDomain> apiList) {
        List<Domain> convertList = new ArrayList<>();
        for (ApiDomain apiDomain : apiList) {
            convertList.add(fromApiToDomain(apiDomain));
        }
        return convertList;
    }

    @Override
    public List<Domain> fromDbToDomainList(List<DbDomain> dbList) {
        List<Domain> convertList = new ArrayList<>();
        for (DbDomain dbDomain : dbList) {
            convertList.add(fromDbToDomain(dbDomain));
        }
        return convertList;
    }

    @Override
    public List<DbDomain> fromApiToDbList(List<ApiDomain> apiList) {
        List<DbDomain> convertList = new ArrayList<>();
        for (ApiDomain apiDomain : apiList) {
            convertList.add(fromApiToDb(apiDomain));
        }
        return convertList;
    }
}
