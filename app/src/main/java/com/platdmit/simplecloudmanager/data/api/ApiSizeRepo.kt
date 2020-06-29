package com.platdmit.simplecloudmanager.data.api

import com.platdmit.simplecloudmanager.data.api.models.ApiSize
import io.reactivex.rxjava3.core.Single

interface ApiSizeRepo {
    fun getSizes(): Single<List<ApiSize>>
}