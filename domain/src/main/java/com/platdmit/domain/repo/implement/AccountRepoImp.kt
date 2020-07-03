package com.platdmit.domain.repo.implement

import com.platdmit.data.api.ApiAccountRepo
import com.platdmit.data.database.DbManager
import com.platdmit.data.database.dao.AccountDao
import com.platdmit.domain.converters.AccountConverter
import com.platdmit.domain.models.UserAccount
import com.platdmit.domain.repo.AccountRepo
import com.platdmit.domain.repo.implement.DomainRepoImp
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.mindrot.BCrypt

class AccountRepoImp(
        private val mApiAccountRepo: ApiAccountRepo,
        dbManager: DbManager,
        private val mAccountConverter: AccountConverter
) : AccountRepo {
    private val mDbAccountRepo: AccountDao = dbManager.mAccountDao()
    override fun getActiveAccount(): Single<UserAccount> {
        return Single.create {
            val dbAccount = mDbAccountRepo.getBaseAccount()
            if (dbAccount != null) {
                val userAccount = mAccountConverter.fromDbToDomain(dbAccount)
                userAccount.pin = dbAccount.pin.toString()
                it.onSuccess(userAccount)
            } else {
                it.onError(Throwable("Not active account"))
            }
        }
    }

    override fun getPrepareAccountInfo(login: String, pass: String): Single<UserAccount> {
        return mApiAccountRepo.getApiKey(login, pass)
                .subscribeOn(Schedulers.newThread())
                .flatMap {
                    val userAccount = mAccountConverter.fromApiToDomain(it.account.account, pass)
                    userAccount.apiKey = it.sessionKey
                    Single.just(userAccount)
                }
                .doOnError {println(it.message)}
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addAccountPin(account: UserAccount): Completable {
        return Completable.create {
            val dbAccount = mAccountConverter.fromDomainToDb(account)
            dbAccount.isMain = true
            dbAccount.pin = BCrypt.hashpw(account.pin, BCrypt.gensalt())
            mDbAccountRepo.insert(dbAccount)
            it.onComplete()
        }.doOnError {println(it.message) }
    }

    companion object {
        private val TAG = DomainRepoImp::class.java.simpleName
    }
}