package com.platdmit.feature_servers.data.retrofit

import com.platdmit.feature_servers.domain.repositories.api.ApiServerRepo
import com.platdmit.mod_servers.data.retrofit.models.*
import com.platdmit.feature_servers.data.retrofit.rest.RestServer
import io.reactivex.rxjava3.core.Single
import java.io.IOException

class ApiServerRepoImp(
        private val restServer: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.rest.RestServer
) : _root_ide_package_.com.platdmit.feature_servers.domain.repositories.api.ApiServerRepo<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiServer, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage> {
    private val TAG = _root_ide_package_.com.platdmit.feature_servers.data.retrofit.ApiServerRepoImp::class.java.simpleName

    override fun getServers(): Single<List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiServer>> {
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

    override fun getServerActions(serverId: Long): Single<List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction>> {
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

    override fun getServerStatistics(serverId: Long): Single<List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic>> {
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

    override fun getServerBackups(serverId: Long): Single<List<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup>> {
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

    override fun getServerLoadAverage(serverId: Long): Single<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage> {
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
}