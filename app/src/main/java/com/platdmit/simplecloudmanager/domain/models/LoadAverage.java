package com.platdmit.simplecloudmanager.domain.models;

public class LoadAverage {
    private String mName;
    private String mTotal;
    private String mValue;
    private double mPercent;

    public LoadAverage(String name, String total, String value, double percent) {
        mName = name;
        mTotal = total;
        mValue = value;
        mPercent = percent;
    }

    public String getName() {
        return mName;
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
