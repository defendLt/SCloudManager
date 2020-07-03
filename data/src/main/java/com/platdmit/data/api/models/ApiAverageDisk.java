package com.platdmit.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiAverageDisk {
    @SerializedName("total")
    @Expose
    public String total;
    @SerializedName("free")
    @Expose
    public String free;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }
}
