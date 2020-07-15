package com.platdmit.simplecloudmanager.di

import com.platdmit.data.api.ApiAccountRepo
import com.platdmit.data.helpers.ActualApiKeyService
import com.platdmit.domain.helpers.ActualApiKeyModule
import com.platdmit.domain.helpers.ActualApiKeyServiceManager
import com.platdmit.domain.helpers.ContentUpdateService
import com.platdmit.domain.helpers.UpdateScheduleService
import com.platdmit.domain.repo.implement.UpdateScheduleRepImp
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
            apiAccountRepo: ApiAccountRepo
    ) : ActualApiKeyService {
        return ActualApiKeyModule(apiAccountRepo)
    }

    @Provides
    fun provideActualApiKeyManager(
            actualApiKeyService: ActualApiKeyService
    ) : ActualApiKeyServiceManager {
        return actualApiKeyService as ActualApiKeyServiceManager
    }

}