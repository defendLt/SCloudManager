package com.platdmit.simplecloudmanager.domain.helpers;

import com.platdmit.simplecloudmanager.domain.repo.UpdateScheduleRepo;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

public class ContentUpdateService implements UpdateScheduleService{
    private static final String TAG = ContentUpdateService.class.getSimpleName();
    private int mLifeTime = 900000;
    private UpdateScheduleRepo mUpdateScheduleRepo;
    private Map<String, DateTime> mActiveTypes;

    public ContentUpdateService(UpdateScheduleRepo updateScheduleRepo) {
        mUpdateScheduleRepo = updateScheduleRepo;
        mActiveTypes = new HashMap<>();
    }

    public ContentUpdateService(UpdateScheduleRepo updateScheduleRepo, int defaultLifeTime) {
        mUpdateScheduleRepo = updateScheduleRepo;
        mActiveTypes = new HashMap<>();
        mLifeTime = defaultLifeTime;
    }

    @Override
    public void setUpdateTime(String key, int timer) {
        DateTime updateTime = new DateTime().plusMillis(timer);
        mActiveTypes.put(key, updateTime);
        mUpdateScheduleRepo.setUpdateTime(key, updateTime.getMillis());
    }

    @Override
    public void setDefaultUpdateTime(String key) {
        setUpdateTime(key, mLifeTime);
    }

    @Override
    public boolean getActualStatus(String key) {
        long updateTime = getUpdateTime(key);
        return new DateTime().getMillis() < updateTime;
    }

    public long getUpdateTime(String key) {
        DateTime updateTime = mActiveTypes.get(key);
        if(updateTime != null){
            return updateTime.getMillis();
        } else {
            long dbUpdateTime = mUpdateScheduleRepo.getUpdateTime(key);
            if(dbUpdateTime > 0){
                mActiveTypes.put(key, new DateTime(dbUpdateTime));
            }
            return dbUpdateTime;
        }
    }
}
