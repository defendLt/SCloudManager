package com.platdmit.data.api.models;

public class ApiRequestResult<M> {
    private boolean mStatus;
    private int mCode;
    private String mMassage;
    private M mResult;

    public ApiRequestResult(boolean status, int code, String massage) {
        mStatus = status;
        mCode = code;
        mMassage = massage;
    }

    public void setResult(M result) {
        mResult = result;
    }

    public int getCode() {
        return mCode;
    }

    public String getMassage() {
        return mMassage;
    }

    public M getResult() {
        return mResult;
    }

    public boolean getStatus() {
        return mStatus;
    }
}
