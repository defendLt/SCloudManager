package com.platdmit.simplecloudmanager.data.api

import com.platdmit.simplecloudmanager.data.api.models.ApiImage
import io.reactivex.rxjava3.core.Single

interface ApiImageRepo {
    fun getImages(): Single<List<ApiImage>>
}