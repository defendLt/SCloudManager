package com.platdmit.simplecloudmanager.data.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DbImage {
    @PrimaryKey
    private long mid;

    private String mName;
    private String mDistribution;
    private String mOsType;

    private int mMinDiskSize;
    private int mMinMemorySize;
    private int mRating;

    public DbImage(long mid, String name, String distribution, String osType, int minDiskSize, int minMemorySize, int rating) {
        this.mid = mid;
        mName = name;
        mDistribution = distribution;
        mOsType = osType;
        mMinDiskSize = minDiskSize;
        mMinMemorySize = minMemorySize;
        mRating = rating;
    }

    public long getMid() {
        return mid;
    }

    public String getName() {
        return mName;
    }

    public String getDistribution() {
        return mDistribution;
    }

    public String getOsType() {
        return mOsType;
    }

    public int getMinDiskSize() {
        return mMinDiskSize;
    }

    public int getMinMemorySize() {
        return mMinMemorySize;
    }

    public int getRating() {
        return mRating;
    }
}
