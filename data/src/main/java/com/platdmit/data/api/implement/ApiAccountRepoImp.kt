package com.platdmit.data.api.implement

import com.platdmit.data.api.ApiAccountRepo
import com.platdmit.data.api.models.ApiAccount
import com.platdmit.data.api.models.ApiAuth
import com.platdmit.data.api.rest.RestAccount
import io.reactivex.rxjava3.core.Single

class ApiAccountRepoImp(
        private val restAccount: RestAccount
) : ApiAccountRepo {
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
                    val apiRequestBody = response.body()!!.account
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