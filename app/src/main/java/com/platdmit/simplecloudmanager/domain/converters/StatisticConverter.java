package com.platdmit.simplecloudmanager.domain.converters;

import com.platdmit.simplecloudmanager.data.api.models.ApiStatistic;
import com.platdmit.simplecloudmanager.data.database.entity.DbStatistic;
import com.platdmit.simplecloudmanager.domain.models.Statistic;

import java.util.List;

public interface StatisticConverter {
    DbStatistic fromApiToDb(ApiStatistic apiStatistic, long serverId);
    Statistic fromDbToDomain(DbStatistic dbStatistic);
    List<DbStatistic> fromApiToDbList(List<ApiStatistic> apiStatistics, long serverId);
    List<Statistic> fromDbToDomainList(List<DbStatistic> dbStatistics);
}
