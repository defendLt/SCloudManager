package com.platdmit.domain.helpers

import com.platdmit.domain.models.UserAccount
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface ActualApiKeyServiceManager : ActualApiKeyService{
    fun setActiveAccount(activeAccount: UserAccount)
    fun startAutoUpdate(activeAccount: UserAccount)
    fun startDemoMode()
    fun stopAutoUpdate()
    fun getAccountStatus(): BehaviorSubject<Boolean>
}