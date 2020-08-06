package com.platdmit.mod_servers.domain.repositories.api

import io.reactivex.rxjava3.core.Single

interface ApiSizeRepo<Size> {
    fun getSizes(): Single<List<Size>>
}