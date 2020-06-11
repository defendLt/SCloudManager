package com.platdmit.simplecloudmanager.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = DbServer.class, parentColumns = "mId", childColumns = "mServer"))
public class DbStatistic {
    @PrimaryKey
    @NonNull
    private String mTag;
    @ColumnInfo(index = true)
    private long mServer;
    private String mTime;
    private float mRamVal;
    private float mCpuVal;

    public DbStatistic(long server, String time, float ramVal, float cpuVal) {
        mTag = server+time;
        mServer = server;
        mTime = time;
        mRamVal = ramVal;
        mCpuVal = cpuVal;
    }

    public long getServer() {
        return mServer;
    }

    public String getTime() {
        return mTime;
    }

    public float getRamVal() {
        return mRamVal;
    }

    public float getCpuVal() {
        return mCpuVal;
    }

    @NonNull
    public String getTag() {
        return mTag;
    }

    public void setTag(@NonNull String tag) {
        mTag = tag;
    }
}
