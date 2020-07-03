package com.platdmit.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiLoadAverage {
    @SerializedName("RAM")
    @Expose
    public ApiAverageRAM rAM;
    @SerializedName("CPU")
    @Expose
    public ApiAverageCPU cPU;
    @SerializedName("disk")
    @Expose
    public ApiAverageDisk disk;
}
