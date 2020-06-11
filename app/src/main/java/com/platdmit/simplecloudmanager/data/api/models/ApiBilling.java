package com.platdmit.simplecloudmanager.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiBilling {
    @SerializedName("payment_date")
    @Expose
    private String paymentDate;
    @SerializedName("payment_amount")
    @Expose
    private String paymentAmount;
    @SerializedName("payment_period")
    @Expose
    private String paymentPeriod;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("payperiod")
    @Expose
    private String payperiod;
    @SerializedName("total_hours")
    @Expose
    private String totalHours;
    @SerializedName("worked_hours")
    @Expose
    private String workedHours;

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(String paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPayperiod() {
        return payperiod;
    }

    public void setPayperiod(String payperiod) {
        this.payperiod = payperiod;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(String workedHours) {
        this.workedHours = workedHours;
    }
}
