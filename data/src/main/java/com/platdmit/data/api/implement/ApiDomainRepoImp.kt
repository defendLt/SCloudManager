package com.platdmit.data.api.implement

import com.platdmit.data.api.ApiDomainRepo
import com.platdmit.data.api.ApiManager
import com.platdmit.data.api.models.ApiDomain
import com.platdmit.data.api.models.ApiDomainRecord
import com.platdmit.data.api.rest.RestDomain
import com.platdmit.data.helpers.ActualApiKeyService
import io.reactivex.rxjava3.core.Single
import java.io.IOException

class ApiDomainRepoImp(private val mActualApiKeyService: ActualApiKeyService) : ApiDomainRepo {
    //always gets the actual key
    private val restDomain: RestDomain
        get() = ApiManager.getApiPoint(RestDomain::class.java, mActualApiKeyService.apiKey)

    override fun getDomains(): Single<List<ApiDomain>> {
        return Single.create {
            try {
                val response = restDomain.getDomains().execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()
                    it.onSuccess(apiRequestBody!!.domains)
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

    override fun getDomain(domainId: Long): Single<ApiDomain> {
        return Single.create {
            try {
                val response = restDomain.getDomain(domainId).execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()
                    it.onSuccess(apiRequestBody!!.domain)
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

    override fun getDomainRecords(domainId: Long): Single<List<ApiDomainRecord>> {
        return Single.create {
            try {
                val response = restDomain.getDomainRecords(domainId).execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()
                    it.onSuccess(apiRequestBody!!.domainRecords)
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

    override fun getDomainRecord(domainId: Int, recordId: Int): Single<ApiDomainRecord> {
        return Single.create {
            try {
                val response = restDomain.getDomain(domainId.toLong()).execute()
                if (response.isSuccessful) {
                    val apiRequestBody = response.body()
                    it.onSuccess(apiRequestBody!!.domainRecord)
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
        private val TAG = ApiDomainRepoImp::class.java.simpleName
    }

}