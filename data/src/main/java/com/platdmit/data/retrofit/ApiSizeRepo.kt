package com.platdmit.data.retrofit

import io.reactivex.rxjava3.core.Single

interface ApiSizeRepo<Size> {
    fun getSizes(): Single<List<Size>>
}