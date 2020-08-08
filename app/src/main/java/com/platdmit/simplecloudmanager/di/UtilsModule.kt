package com.platdmit.simplecloudmanager.di

import com.platdmit.data.UpdateScheduleRepImp
import com.platdmit.data.retrofit.ApiAccountRepoImp
import com.platdmit.domain.utilities.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UtilsModule {

    @Provides
    fun provideUpdateScheduleService(
            updateScheduleRepo : UpdateScheduleRepImp
    ) : UpdateScheduleService {
        return ContentUpdateService(updateScheduleRepo)
    }

    @Singleton
    @Provides
    fun provideActualApiKeyService(
            apiAccountRepo: ApiAccountRepoImp
    ) : ActualApiKeyService {
        return ActualApiKeyModule()
    }

    @Provides
    fun provideActualApiKeyManager(
            actualApiKeyService: ActualApiKeyService
    ) : ActualApiKeyServiceManager {
        return actualApiKeyService as ActualApiKeyServiceManager
    }

}