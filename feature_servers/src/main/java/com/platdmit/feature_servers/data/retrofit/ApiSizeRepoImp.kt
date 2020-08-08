package com.platdmit.feature_servers.data.retrofit

import com.platdmit.feature_servers.domain.repositories.api.ApiSizeRepo
import com.platdmit.feature_servers.data.retrofit.models.ApiSize
import com.platdmit.feature_servers.data.retrofit.rest.RestSize
import io.reactivex.rxjava3.core.Single

class ApiSizeRepoImp(
        private val restSize: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.rest.RestSize
) : _root_ide_package_.com.platdmit.feature_servers.domain.repositories.api.ApiSizeRepo<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiSize> {
    private val TAG = _root_ide_package_.com.platdmit.feature_servers.data.retrofit.ApiSizeRepoImp::class.java.simpleName

    override fun getSizes(): Single<List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiSize>> {
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