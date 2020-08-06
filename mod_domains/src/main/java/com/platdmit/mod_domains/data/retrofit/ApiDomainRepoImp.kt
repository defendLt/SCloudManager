package com.platdmit.mod_domains.data.retrofit

import com.platdmit.mod_domains.domain.repositories.api.ApiDomainRepo
import com.platdmit.mod_domains.data.retrofit.models.ApiDomain
import com.platdmit.mod_domains.data.retrofit.models.ApiDomainRecord
import com.platdmit.mod_domains.data.retrofit.rest.RestDomain
import io.reactivex.rxjava3.core.Single
import java.io.IOException

class ApiDomainRepoImp(
        private val restDomain: RestDomain
) : ApiDomainRepo<ApiDomain, ApiDomainRecord> {
    private val TAG = ApiDomainRepoImp::class.java.simpleName

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
}