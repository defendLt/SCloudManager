package com.platdmit.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiNetworks {
    @SerializedName("v4")
    @Expose
    private List<ApiV4> v4 = null;

    public ApiV4 getV4() {
        return v4.get(0);
    }

    public void setV4(ApiV4 v4) {
        this.v4.set(0, v4);
    }
}
