package com.platdmit.feature_login.data.retrofit

import com.platdmit.feature_login.domain.repositories.api.ApiAccountRepo
import com.platdmit.feature_login.data.retrofit.models.ApiAccount
import com.platdmit.feature_login.data.retrofit.models.ApiAuth
import com.platdmit.feature_login.data.retrofit.rest.RestAccount
import io.reactivex.rxjava3.core.Single

class ApiAccountRepoImp(
        private val restAccount: RestAccount
) : ApiAccountRepo<ApiAuth, ApiAccount> {
    override fun getApiKey(login: String, pass: String): Single<ApiAuth> {
        return Single.create {
            try {
                val response = restAccount.getApiKey(login, pass).execute()
                if (response.isSuccessful) {
                    it.onSuccess(response.body())
                } else {
                    throw Throwable(response.message())
                }
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    override fun getActiveAccount(): Single<ApiAccount> {
        return Single.create {
            try {
                val response = restAccount.getAccount().execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()!!.requestBody
                    it.onSuccess(apiRequestBody.account)
                } else {
                    throw Throwable(response.message())
                }
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}