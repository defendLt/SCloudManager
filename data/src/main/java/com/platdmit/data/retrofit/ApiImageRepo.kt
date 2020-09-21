package com.platdmit.data.retrofit

import io.reactivex.rxjava3.core.Single

interface ApiImageRepo<Image> {
    fun getImages(): Single<List<Image>>
}