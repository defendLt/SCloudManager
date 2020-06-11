package com.platdmit.simplecloudmanager.domain.converters;

import com.platdmit.simplecloudmanager.data.api.models.ApiSize;
import com.platdmit.simplecloudmanager.data.database.entity.DbSize;
import com.platdmit.simplecloudmanager.domain.models.Size;

import java.util.List;

public interface SizeConverter {
    DbSize fromApiToDb(ApiSize apiSize);
    Size fromDbToDomain(DbSize dbSize);
    List<Size> fromDbToDomainList(List<DbSize> dbList);
    List<DbSize> fromApiToDbList(List<ApiSize> apiList);
}
