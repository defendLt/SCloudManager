package com.platdmit.data.api.implement

import com.platdmit.data.api.ApiImageRepo
import com.platdmit.data.api.ApiManager.getApiPoint
import com.platdmit.data.api.implement.ApiDomainRepoImp
import com.platdmit.data.api.models.ApiImage
import com.platdmit.data.api.rest.RestImage
import com.platdmit.data.helpers.ActualApiKeyService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter

class ApiImageRepoImp(private val mActualApiKeyService: ActualApiKeyService) : ApiImageRepo {
    //always gets the actual key
    private val restImage: RestImage
        get() = getApiPoint(RestImage::class.java, mActualApiKeyService.apiKey)

    override fun getImages(): Single<List<ApiImage>> {
        return Single.create {
            try {
                val response = restImage.getImages().execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()
                    it.onSuccess(apiRequestBody!!.images)
                } else {
                    throw Throwable(response.message())
                }
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    companion object {
        private val TAG = ApiDomainRepoImp::class.java.simpleName
    }

}