package com.platdmit.simplecloudmanager.data.api;

import com.platdmit.simplecloudmanager.data.api.models.ApiSize;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ApiSizeRepo {
    Single<List<ApiSize>> getSizes();
}
