package com.platdmit.simplecloudmanager.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = DbServer.class, parentColumns = "mId", childColumns = "mServer"))
public class DbLoadAverage {
    @PrimaryKey
    @NonNull
    private String mName;
    @ColumnInfo(index = true)
    private long mServer;
    private String mTotal;
    private String mValue;
    private double mPercent;

    public DbLoadAverage(@NonNull String name, long server, String total, String value, double percent) {
        mName = name;
        mServer = server;
        mTotal = total;
        mValue = value;
        mPercent = percent;
    }

    public String getName() {
        return mName;
    }

    public long getServer() {
        return mServer;
    }

    public String getTotal() {
        return mTotal;
    }

    public String getValue() {
        return mValue;
    }

    public double getPercent() {
        return mPercent;
    }
}
