package com.platdmit.simplecloudmanager.domain.repo;

import com.platdmit.simplecloudmanager.domain.models.LoadAverage;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.core.Observable;

public interface ServerLoadAveragesRepo {
    Observable<List<LoadAverage>> getServerLoadAverages(long id);
}
