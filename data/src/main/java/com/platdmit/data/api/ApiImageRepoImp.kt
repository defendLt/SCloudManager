package com.platdmit.data.api

import com.platdmit.domain.repositories.api.ApiImageRepo
import com.platdmit.data.api.models.ApiImage
import com.platdmit.data.api.rest.RestImage
import io.reactivex.rxjava3.core.Single

class ApiImageRepoImp(
        private val restImage: RestImage
) : ApiImageRepo<ApiImage> {
    private val TAG = ApiDomainRepoImp::class.java.simpleName

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
}