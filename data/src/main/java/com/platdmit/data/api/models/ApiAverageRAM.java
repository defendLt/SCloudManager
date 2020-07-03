package com.platdmit.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiAverageRAM {
    @SerializedName("total")
    @Expose
    public String total;
    @SerializedName("stats")
    @Expose
    public String stats;
    @SerializedName("now")
    @Expose
    public double now;
    @SerializedName("hourly")
    @Expose
    public double hourly;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public double getNow() {
        return now;
    }

    public void setNow(double now) {
        this.now = now;
    }

    public double getHourly() {
        return hourly;
    }

    public void setHourly(double hourly) {
        this.hourly = hourly;
    }
}
