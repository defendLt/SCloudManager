package com.platdmit.simplecloudmanager.domain.converters.implement;

import com.platdmit.simplecloudmanager.data.api.models.ApiStatistic;
import com.platdmit.simplecloudmanager.data.database.entity.DbStatistic;
import com.platdmit.simplecloudmanager.domain.converters.StatisticConverter;
import com.platdmit.simplecloudmanager.domain.models.Statistic;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class StatisticConvertImp implements StatisticConverter{

    @Override
    public DbStatistic fromApiToDb(ApiStatistic apiStatistic, long serverId) {
        return new DbStatistic(
                serverId,
                timeConvert(apiStatistic.getTime()),
                Float.parseFloat(apiStatistic.getRam()),
                Float.parseFloat(apiStatistic.getCpu())
        );
    }

    @Override
    public Statistic fromDbToDomain(DbStatistic dbStatistic) {
        return new Statistic(
                dbStatistic.getTime(),
                dbStatistic.getRamVal(),
                dbStatistic.getCpuVal()
        );
    }

    @Override
    public List<DbStatistic> fromApiToDbList(List<ApiStatistic> apiStatistics, long serverId) {
        List<DbStatistic> convertList = new ArrayList<>();
        for (ApiStatistic apiStatistic : apiStatistics) {
            try {
                convertList.add(fromApiToDb(apiStatistic, serverId));
            } catch (NullPointerException ignored){}
        }
        return convertList;
    }

    @Override
    public List<Statistic> fromDbToDomainList(List<DbStatistic> dbStatistics) {
        List<Statistic> convertList = new ArrayList<>();
        for (DbStatistic dbStatistic : dbStatistics) {
            try {
                convertList.add(fromDbToDomain(dbStatistic));
            } catch (NullPointerException ignored){}
        }
        return convertList;
    }

    private String timeConvert(String date){
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("DD.MM.yyyy HH:mm:SS");
            return DateTimeFormat.forPattern("HH:mm").print(dateTimeFormatter.parseDateTime(date));
        } catch (NullPointerException | IllegalArgumentException ignored){
            return "";
        }
    }
}
