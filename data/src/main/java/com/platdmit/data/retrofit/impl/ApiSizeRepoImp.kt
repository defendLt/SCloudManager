package com.platdmit.data.retrofit.impl

import com.platdmit.data.retrofit.ApiSizeRepo
import com.platdmit.data.retrofit.models.ApiSize
import com.platdmit.data.retrofit.rest.RestSize
import io.reactivex.rxjava3.core.Single

class ApiSizeRepoImp(
        private val restSize: RestSize
) : ApiSizeRepo<ApiSize> {
    private val TAG = ApiSizeRepoImp::class.java.simpleName

    override fun getSizes(): Single<List<ApiSize>> {
        return Single.create {
            try {
                val response = restSize.getSizes().execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()
                    it.onSuccess(apiRequestBody!!.sizes)
                } else {
                    throw Throwable(response.message())
                }
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

}