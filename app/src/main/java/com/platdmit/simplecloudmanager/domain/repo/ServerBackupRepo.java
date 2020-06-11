package com.platdmit.simplecloudmanager.domain.repo;

import com.platdmit.simplecloudmanager.domain.models.Backup;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.core.Observable;

public interface ServerBackupRepo {
    Observable<List<Backup>> getServerBackups(long id);
}
