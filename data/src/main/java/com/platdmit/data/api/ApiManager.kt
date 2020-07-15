package com.platdmit.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Deprecated("Implementation updated, moved to di")
object ApiManager {
    private val TAG = ApiManager::class.java.simpleName
    private const val BASE_URL = "https://api.simplecloud.ru/v3/"
    private var API_KEY: String? = null
    private val okHttpInstance: OkHttpClient
        get() = OkHttpClient.Builder()
                .addInterceptor{
                    val newRequest = it.request().newBuilder().header("Authorization", "Bearer $API_KEY").build()
                    it.proceed(newRequest)
                }
                .addInterceptor(okHttpLogLevel).build()

    private val okHttpLogLevel: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            return httpLoggingInterceptor
        }

    private val retrofitInstance: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpInstance)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    @JvmStatic
    fun <T> getApiPoint(rest: Class<T>, api_key: String): T {
        API_KEY = api_key
        return retrofitInstance.create(rest) as T
    }
}