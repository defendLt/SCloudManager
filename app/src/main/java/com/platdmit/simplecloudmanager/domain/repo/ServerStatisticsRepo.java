package com.platdmit.simplecloudmanager.domain.repo;

import com.platdmit.simplecloudmanager.domain.models.Statistic;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface ServerStatisticsRepo {
    Observable<List<Statistic>> getServerStatistics(long id);
}
