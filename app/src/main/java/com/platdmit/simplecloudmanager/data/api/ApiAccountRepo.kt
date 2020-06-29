package com.platdmit.simplecloudmanager.data.api

import com.platdmit.simplecloudmanager.data.api.models.ApiAccount
import com.platdmit.simplecloudmanager.data.api.models.ApiAuth
import io.reactivex.rxjava3.core.Single

interface ApiAccountRepo {
    fun getApiKey(login: String, pass: String): Single<ApiAuth>
    fun getActiveAccount(): Single<ApiAccount>
}