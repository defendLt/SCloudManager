package com.platdmit.simplecloudmanager.di

import com.platdmit.base_utils.apikey_service.domain.ActualApiKeyModule
import com.platdmit.base_utils.apikey_service.domain.ActualApiKeyService
import com.platdmit.base_utils.apikey_service.domain.ActualApiKeyServiceManager
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
            updateScheduleRepo : com.platdmit.base_utils.update_service.data.UpdateScheduleRepImp
    ) : com.platdmit.base_utils.update_service.domain.UpdateScheduleService {
        return com.platdmit.base_utils.update_service.domain.ContentUpdateService(updateScheduleRepo)
    }

    @Singleton
    @Provides
    fun provideActualApiKeyService(
//            apiAccountRepo: ApiAccountRepoImp
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