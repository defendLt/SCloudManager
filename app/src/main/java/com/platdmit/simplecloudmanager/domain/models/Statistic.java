package com.platdmit.simplecloudmanager.domain.models;

public class Statistic {
    private String mTime;

    private float mRamVal;
    private float mCpuVal;

    public Statistic(String time, float ramVal, float cpuVal) {
        mTime = time;
        mRamVal = ramVal;
        mCpuVal = cpuVal;
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
}
