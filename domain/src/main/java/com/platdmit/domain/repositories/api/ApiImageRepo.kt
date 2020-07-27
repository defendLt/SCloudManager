package com.platdmit.domain.repositories.api

import io.reactivex.rxjava3.core.Single

interface ApiImageRepo<Image> {
    fun getImages(): Single<List<Image>>
}