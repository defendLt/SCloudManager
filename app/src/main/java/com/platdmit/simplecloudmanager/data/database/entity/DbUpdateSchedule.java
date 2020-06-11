package com.platdmit.simplecloudmanager.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DbUpdateSchedule {
    @PrimaryKey
    @NonNull
    private String mName;

    private long mUpdateInterval;
    private long mLastUpdate;
    private long mNextUpdate;

    public DbUpdateSchedule(@NonNull String name, long nextUpdate) {
        mName = name;
        mNextUpdate = nextUpdate;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getUpdateInterval() {
        return mUpdateInterval;
    }

    public void setUpdateInterval(long updateInterval) {
        mUpdateInterval = updateInterval;
    }

    public long getLastUpdate() {
        return mLastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        mLastUpdate = lastUpdate;
    }

    public long getNextUpdate() {
        return mNextUpdate;
    }

    public void setNextUpdate(long nextUpdate) {
        mNextUpdate = nextUpdate;
    }
}
