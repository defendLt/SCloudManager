package com.platdmit.simplecloudmanager.data.api.implement

import com.platdmit.simplecloudmanager.data.api.ApiManager
import com.platdmit.simplecloudmanager.data.api.ApiServerRepo
import com.platdmit.simplecloudmanager.data.api.models.*
import com.platdmit.simplecloudmanager.data.api.rest.RestServer
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService
import io.reactivex.rxjava3.core.Single
import java.io.IOException

class ApiServerRepoImp(private val mActualApiKeyService: ActualApiKeyService) : ApiServerRepo {
    //always gets the actual key
    private val restServer: RestServer
        get() =//always gets the actual key
            ApiManager.getApiPoint(RestServer::class.java, mActualApiKeyService.apiKey)

    override fun getServers(): Single<List<ApiServer>> {
        return Single.create {
            try {
                val response = restServer.getServers().execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()
                    it.onSuccess(apiRequestBody!!.servers)
                } else {
                    throw Throwable(response.message())
                }
            } catch (e: IOException) {
                println(TAG + e.localizedMessage)
            } catch (e: Exception) {
                println(TAG + e.localizedMessage)
                it.onError(e)
            }
        }
    }

    override fun getServerActions(serverId: Long): Single<List<ApiAction>> {
        return Single.create {
            try {
                val response = restServer.getServerActions(serverId).execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()
                    it.onSuccess(apiRequestBody!!.serverActions)
                } else {
                    throw Throwable(response.message())
                }
            } catch (e: IOException) {
                println(TAG + e.localizedMessage)
            } catch (e: Exception) {
                println(TAG + e.localizedMessage)
                it.onError(e)
            }
        }
    }

    override fun getServerStatistics(serverId: Long): Single<List<ApiStatistic>> {
        return Single.create {
            try {
                val response = restServer.getServerStatistics(serverId).execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()
                    it.onSuccess(apiRequestBody!!.serverStatistics)
                } else {
                    throw Throwable(response.message())
                }
            } catch (e: IOException) {
                println(TAG + e.localizedMessage)
            } catch (e: Exception) {
                println(TAG + e.localizedMessage)
                it.onError(e)
            }
        }
    }

    override fun getServerBackups(serverId: Long): Single<List<ApiBackup>> {
        return Single.create {
            try {
                val response = restServer.getServerBackups(serverId).execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()
                    it.onSuccess(apiRequestBody!!.serverBackups)
                } else {
                    throw Throwable(response.message())
                }
            } catch (e: IOException) {
                println(TAG + e.localizedMessage)
            } catch (e: Exception) {
                println(TAG + e.localizedMessage)
                it.onError(e)
            }
        }
    }

    override fun getServerLoadAverage(serverId: Long): Single<ApiLoadAverage> {
        return Single.create {
            try {
                val response = restServer.getServerLoadAverage(serverId).execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()
                    it.onSuccess(apiRequestBody!!.serverLoadAverage)
                } else {
                    throw Throwable(response.message())
                }
            } catch (e: IOException) {
                println(TAG + e.localizedMessage)
            } catch (e: Exception) {
                println(TAG + e.localizedMessage)
                it.onError(e)
            }
        }
    }

    companion object {
        private val TAG = ApiServerRepoImp::class.java.simpleName
    }

}