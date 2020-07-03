package com.platdmit.data.api

import com.platdmit.data.api.models.ApiSize
import io.reactivex.rxjava3.core.Single

interface ApiSizeRepo {
    fun getSizes(): Single<List<ApiSize>>
}