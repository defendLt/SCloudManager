package com.platdmit.simplecloudmanager.domain.repo;

import com.platdmit.simplecloudmanager.domain.models.Server;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface ServerBaseRepo {
    Observable<List<Server>> getServers();
    Single<Server> getServer(long id);
    void nextUpdate();
}
