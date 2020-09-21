package com.platdmit.simplecloudmanager.di

import com.platdmit.data.retrofit.impl.*
import com.platdmit.data.retrofit.rest.*
import com.platdmit.domain.utilities.ActualApiKeyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthOkHttpInstance

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseOkHttpInstance

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseRetrofit

    @Singleton
    @Provides
    fun provideOkHttpLogLevel() : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return httpLoggingInterceptor
    }

    @AuthOkHttpInstance
    @Singleton
    @Provides
    fun provideAuthOkHttpInstance(
            httpLoggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @BaseOkHttpInstance
    @Singleton
    @Provides
    fun provideBaseOkHttpInstance(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            actualApiKeyService: ActualApiKeyService
    ) : OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor{
                    val newRequest = it.request()
                            .newBuilder()
                            .header(
                                    "Authorization",
                                    "Bearer ${actualApiKeyService.apiKey}"
                            ).build()
                    it.proceed(newRequest)
                }
                .addInterceptor(httpLoggingInterceptor).build()
    }

    @AuthRetrofit
    @Singleton
    @Provides
    fun provideAuthRetrofit(
            @AuthOkHttpInstance okHttpClient: OkHttpClient
    ) : Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl("https://api.simplecloud.ru/v3/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
    }

    @BaseRetrofit
    @Singleton
    @Provides
    fun provideBaseRetrofit(
            @BaseOkHttpInstance okHttpClient: OkHttpClient
    ) : Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl("https://api.simplecloud.ru/v3/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideAccountRest(
            @AuthRetrofit retrofit: Retrofit.Builder
    ) : RestAccount {
        return retrofit
                .build()
                .create(RestAccount::class.java)
    }

    @Singleton
    @Provides
    fun provideDomainRest(
            @BaseRetrofit retrofit: Retrofit.Builder
    ) : RestDomain {
        return retrofit
                .build()
                .create(RestDomain::class.java)
    }

    @Singleton
    @Provides
    fun provideImageRest(
            @BaseRetrofit retrofit: Retrofit.Builder
    ) : RestImage {
        return retrofit
                .build()
                .create(RestImage::class.java)
    }

    @Singleton
    @Provides
    fun provideServerRest(
            @BaseRetrofit retrofit: Retrofit.Builder
    ) : RestServer {
        return retrofit
                .build()
                .create(RestServer::class.java)
    }

    @Singleton
    @Provides
    fun provideSizeRest(
            @BaseRetrofit retrofit: Retrofit.Builder
    ) : RestSize {
        return retrofit
                .build()
                .create(RestSize::class.java)
    }

    @Singleton
    @Provides
    fun provideApiAccountRepo(
            restAccount : RestAccount
    ) : ApiAccountRepoImp {
        return ApiAccountRepoImp(restAccount)
    }

    @Singleton
    @Provides
    fun provideApiDomainRepo(
            restDomain: RestDomain
    ) : ApiDomainRepoImp {
        return ApiDomainRepoImp(restDomain)
    }

    @Singleton
    @Provides
    fun provideApiImageRepo(
            restImage: RestImage
    ) : ApiImageRepoImp {
        return ApiImageRepoImp(restImage)
    }

    @Singleton
    @Provides
    fun provideApiServerRepo(
            restServer: RestServer
    ) : ApiServerRepoImp {
        return ApiServerRepoImp(restServer)
    }

    @Singleton
    @Provides
    fun provideApiSizeRepo(
            restSize: RestSize
    ) : ApiSizeRepoImp {
        return ApiSizeRepoImp(restSize)
    }
}