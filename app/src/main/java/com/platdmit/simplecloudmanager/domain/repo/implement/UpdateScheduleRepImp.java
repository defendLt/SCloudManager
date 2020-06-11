package com.platdmit.simplecloudmanager.domain.repo.implement;

import com.platdmit.simplecloudmanager.data.database.DbManager;
import com.platdmit.simplecloudmanager.data.database.dao.UpdateScheduleDao;
import com.platdmit.simplecloudmanager.data.database.entity.DbUpdateSchedule;
import com.platdmit.simplecloudmanager.domain.repo.UpdateScheduleRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateScheduleRepImp implements UpdateScheduleRepo {
    private static final String TAG = UpdateScheduleRepImp.class.getSimpleName();
    private UpdateScheduleDao mDbManager;

    public UpdateScheduleRepImp(DbManager dbManager) {
        mDbManager = dbManager.mUpdateScheduleDao();
    }

    @Override
    public List<DbUpdateSchedule> getUpdateTimeList() {
        return mDbManager.getScheduleAll();
    }

    @Override
    public long getUpdateTime(String key) {
        long nextUpdate;
        try {
            nextUpdate = mDbManager.getScheduleForName(key).getNextUpdate();
        } catch (Exception e){
            nextUpdate = 0;
        }
        return nextUpdate;
    }

    @Override
    public void setUpdateTime(String key, long nextUpdate) {
         Completable.fromAction(() -> {
            mDbManager.insert(new DbUpdateSchedule(key, nextUpdate));
        }).observeOn(Schedulers.io()).subscribe();
    }
}
