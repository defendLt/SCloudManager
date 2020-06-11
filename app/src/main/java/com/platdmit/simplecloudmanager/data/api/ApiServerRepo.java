package com.platdmit.simplecloudmanager.data.api;

import com.platdmit.simplecloudmanager.data.api.models.ApiAction;
import com.platdmit.simplecloudmanager.data.api.models.ApiBackup;
import com.platdmit.simplecloudmanager.data.api.models.ApiLoadAverage;
import com.platdmit.simplecloudmanager.data.api.models.ApiServer;
import com.platdmit.simplecloudmanager.data.api.models.ApiStatistic;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ApiServerRepo {
    Single<List<ApiServer>> getServers();
    Single<List<ApiAction>> getServerActions(long serverId);
    Single<List<ApiStatistic>> getServerStatistics(long serverId);
    Single<List<ApiBackup>> getServerBackups(long serverId);
    Single<ApiLoadAverage> getServerLoadAverage(long serverId);
}
