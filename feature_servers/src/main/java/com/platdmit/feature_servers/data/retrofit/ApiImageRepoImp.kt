package com.platdmit.feature_servers.data.retrofit

import com.platdmit.mod_domains.data.retrofit.ApiDomainRepoImp
import com.platdmit.feature_servers.domain.repositories.api.ApiImageRepo
import com.platdmit.feature_servers.data.retrofit.models.ApiImage
import com.platdmit.feature_servers.data.retrofit.rest.RestImage
import io.reactivex.rxjava3.core.Single

class ApiImageRepoImp(
        private val restImage: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.rest.RestImage
) : _root_ide_package_.com.platdmit.feature_servers.domain.repositories.api.ApiImageRepo<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiImage> {
    private val TAG = ApiDomainRepoImp::class.java.simpleName

    override fun getImages(): Single<List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiImage>> {
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