package com.platdmit.domain.utilities

import com.platdmit.domain.BuildConfig
import com.platdmit.domain.models.UserAccount
import com.platdmit.domain.repositories.AuthRepo
import io.reactivex.rxjava3.core.Completable

class ActualApiKeyModule(
        private val authRepo: AuthRepo
) : ActualApiKeyServiceManager {
    private lateinit var activeUserAccount: UserAccount
    override var apiKey: String = ""

    override fun setActiveAccount(activeAccount: UserAccount) {
        activeUserAccount = activeAccount
        refreshApiKey().subscribe().dispose()
    }

    override fun getAccountStatus(): Completable {
        return Completable.create{ status ->
            if (apiKey.isEmpty()){
                status.onError(Throwable("Not active"))
            } else {
                status.onComplete()
            }
        }
    }

    override fun startDemoMode() {
        apiKey = DEMO_API_KEY
    }

    override fun refreshApiKey(): Completable {
        return Completable.create { status ->
            authRepo.getApiKey(activeUserAccount)
                    .subscribe({
                        apiKey = it
                        status.onComplete()
                    }, {
                        status.onError(it)
                    })
        }
    }

    companion object {
        const val DEMO_API_KEY = BuildConfig.DEMO_API_KEY
    }
}