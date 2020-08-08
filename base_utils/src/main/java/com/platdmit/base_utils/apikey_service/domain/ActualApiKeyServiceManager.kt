package com.platdmit.base_utils.apikey_service.domain

import com.platdmit.feature_login.domain.models.UserAccount
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface ActualApiKeyServiceManager : ActualApiKeyService {
    fun setActiveAccount(activeAccount: UserAccount)
    fun startAutoUpdate(activeAccount: UserAccount)
    fun startDemoMode()
    fun stopAutoUpdate()
    fun getAccountStatus(): BehaviorSubject<Boolean>
}