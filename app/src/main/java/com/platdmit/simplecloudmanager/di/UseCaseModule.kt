package com.platdmit.simplecloudmanager.di

import com.platdmit.domain.repositories.AccountRepo
import com.platdmit.domain.usecase.LoginUseCase
import com.platdmit.domain.usecase.impl.LoginUseCaseImpl
import com.platdmit.domain.utilities.ActualApiKeyServiceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(
            accountRepo: AccountRepo,
            actualApiKeyServiceManager: ActualApiKeyServiceManager
    ): LoginUseCase{
        return LoginUseCaseImpl(accountRepo, actualApiKeyServiceManager)
    }
}