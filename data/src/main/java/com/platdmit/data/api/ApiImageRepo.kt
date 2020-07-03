package com.platdmit.data.api

import com.platdmit.data.api.models.ApiImage
import io.reactivex.rxjava3.core.Single

interface ApiImageRepo {
    fun getImages(): Single<List<ApiImage>>
}