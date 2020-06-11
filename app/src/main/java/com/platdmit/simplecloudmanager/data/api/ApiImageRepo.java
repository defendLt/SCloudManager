package com.platdmit.simplecloudmanager.data.api;

import com.platdmit.simplecloudmanager.data.api.models.ApiImage;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ApiImageRepo {
    Single<List<ApiImage>> getImages();
}
