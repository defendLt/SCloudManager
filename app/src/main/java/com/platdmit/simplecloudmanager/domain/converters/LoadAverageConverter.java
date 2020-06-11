package com.platdmit.simplecloudmanager.domain.converters;

import com.platdmit.simplecloudmanager.data.api.models.ApiLoadAverage;
import com.platdmit.simplecloudmanager.data.database.entity.DbLoadAverage;
import com.platdmit.simplecloudmanager.domain.models.LoadAverage;

import java.util.List;

public interface LoadAverageConverter {
    List<DbLoadAverage> fromApiToDb(ApiLoadAverage apiLoadAverage, long serverId);
    List<LoadAverage> fromApiToDomain(ApiLoadAverage apiLoadAverage);
    List<LoadAverage> fromDbToDomain(List<DbLoadAverage> dbLoadAverages);
}
