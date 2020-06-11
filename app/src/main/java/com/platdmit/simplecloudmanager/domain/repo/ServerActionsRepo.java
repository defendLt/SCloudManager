package com.platdmit.simplecloudmanager.domain.repo;
import com.platdmit.simplecloudmanager.domain.models.Action;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.core.Observable;

public interface ServerActionsRepo {
    Observable<List<Action>> getServerActions(long id);
}
