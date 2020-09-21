package com.platdmit.data.retrofit.impl

import com.platdmit.data.retrofit.ApiServerRepo
import com.platdmit.data.retrofit.models.*
import com.platdmit.data.retrofit.rest.RestServer
import io.reactivex.rxjava3.core.Single
import java.io.IOException

class ApiServerRepoImp(
        private val restServer: RestServer
) : ApiServerRepo<ApiServer, ApiAction, ApiStatistic, ApiBackup, ApiLoadAverage> {
    private val TAG = ApiServerRepoImp::class.java.simpleName

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
                    it.onSuccess(apiRequestBody!!.actions)
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
                    it.onSuccess(apiRequestBody!!.statistics)
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
                    it.onSuccess(apiRequestBody!!.backups)
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
                    it.onSuccess(apiRequestBody!!.loadAverage)
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
}