package com.platdmit.data.api

import com.platdmit.data.api.models.ApiAccount
import com.platdmit.data.api.models.ApiAuth
import io.reactivex.rxjava3.core.Single

interface ApiAccountRepo {
    fun getApiKey(login: String, pass: String): Single<ApiAuth>
    fun getActiveAccount(): Single<ApiAccount>
}