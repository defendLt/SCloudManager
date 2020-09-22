package com.platdmit.domain.utilities

import com.platdmit.domain.models.UserAccount
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface ActualApiKeyServiceManager : ActualApiKeyService{
    fun setActiveAccount(activeAccount: UserAccount)
    fun getAccountStatus() : Completable
    fun startDemoMode()
}