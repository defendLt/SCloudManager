package com.platdmit.data.api.implement

import com.platdmit.data.api.ApiManager.getApiPoint
import com.platdmit.data.api.ApiSizeRepo
import com.platdmit.data.api.models.ApiSize
import com.platdmit.data.api.rest.RestSize
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService
import io.reactivex.rxjava3.core.Single

class ApiSizeRepoImp(private val mActualApiKeyService: ActualApiKeyService) : ApiSizeRepo {
    //always gets the actual key
    private val restSize: RestSize
        get() = getApiPoint(RestSize::class.java, mActualApiKeyService.apiKey)

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

    companion object {
        private val TAG = ApiSizeRepoImp::class.java.simpleName
    }

}