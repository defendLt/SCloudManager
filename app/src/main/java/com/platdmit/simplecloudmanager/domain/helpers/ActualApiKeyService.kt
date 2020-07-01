package com.platdmit.simplecloudmanager.domain.helpers

import com.platdmit.simplecloudmanager.domain.models.UserAccount
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface ActualApiKeyService {
    fun setActiveAccount(activeAccount: UserAccount)
    fun startAutoUpdate(activeAccount: UserAccount)
    fun startDemoMode()
    fun stopAutoUpdate()
    fun getAccountStatus(): BehaviorSubject<Boolean>
    var apiKey: String
}