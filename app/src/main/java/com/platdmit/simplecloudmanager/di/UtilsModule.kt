package com.platdmit.simplecloudmanager.di

import com.platdmit.data.UpdateScheduleRepImp
import com.platdmit.domain.repositories.AuthRepo
import com.platdmit.domain.utilities.*
import com.platdmit.simplecloudmanager.utilities.ErrorMassageHandler
import com.platdmit.simplecloudmanager.utilities.ErrorMessageHandlerImp
import com.platdmit.simplecloudmanager.utilities.NetworkHelper
import com.platdmit.simplecloudmanager.utilities.NetworkHelperImpl
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
            authRepo: AuthRepo
    ) : ActualApiKeyService {
        return ActualApiKeyModule(authRepo)
    }

    @Provides
    fun provideActualApiKeyManager(
            actualApiKeyService: ActualApiKeyService
    ) : ActualApiKeyServiceManager {
        return actualApiKeyService as ActualApiKeyServiceManager
    }

    @Singleton
    @Provides
    fun bindNetworkHelper(
            networkHelperImpl: NetworkHelperImpl
    ) : NetworkHelper {
        return networkHelperImpl
    }

    @Singleton
    @Provides
    fun bindErrorMessageHandler(
            errorMessageHandlerImp: ErrorMessageHandlerImp
    ) : ErrorMassageHandler {
        return errorMessageHandlerImp
    }

}