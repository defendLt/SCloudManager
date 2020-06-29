package com.platdmit.simplecloudmanager.data.api.implement

import com.platdmit.simplecloudmanager.data.api.ApiAccountRepo
import com.platdmit.simplecloudmanager.data.api.ApiManager
import com.platdmit.simplecloudmanager.data.api.models.ApiAccount
import com.platdmit.simplecloudmanager.data.api.models.ApiAuth
import com.platdmit.simplecloudmanager.data.api.rest.RestAccount
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter

class ApiAccountRepoImp : ApiAccountRepo {
    private val mSRestAccount: RestAccount = ApiManager.getApiPoint(RestAccount::class.java, "")

    override fun getApiKey(login: String, pass: String): Single<ApiAuth> {
        return Single.create {
            try {
                val response = mSRestAccount.getApiKey(login, pass).execute()
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
                val response = mSRestAccount.account.execute()
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

    companion object {
        private val TAG = ApiAccountRepoImp::class.java.simpleName
    }

}