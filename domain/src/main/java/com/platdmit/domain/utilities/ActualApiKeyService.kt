package com.platdmit.domain.utilities

import io.reactivex.rxjava3.core.Completable

interface ActualApiKeyService {
    var apiKey: String
    fun refreshApiKey(): Completable
}