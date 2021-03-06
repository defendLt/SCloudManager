package com.platdmit.data

import com.platdmit.data.retrofit.ApiAccountRepo
import com.platdmit.data.retrofit.models.ApiAccount
import com.platdmit.data.retrofit.models.ApiAuth
import com.platdmit.data.room.dao.AccountDao
import com.platdmit.data.room.entity.DbAccount
import com.platdmit.domain.converters.AccountConverter
import com.platdmit.domain.models.UserAccount
import com.platdmit.domain.repositories.AccountRepo
import com.platdmit.domain.repositories.AuthRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.mindrot.BCrypt

class AccountRepoImp(
        private val apiAccountRepo: ApiAccountRepo<ApiAuth, ApiAccount>,
        private val accountDao: AccountDao,
        private val accountConverter: AccountConverter<ApiAccount, UserAccount, DbAccount>
) : AccountRepo, AuthRepo {

    override fun getActiveAccount(): Single<UserAccount> {
        return Single.create {
            val dbAccount = accountDao.getBaseAccount()
            if (dbAccount != null) {
                val userAccount = accountConverter.fromDbToDomain(dbAccount)
                userAccount.pin = dbAccount.pin.toString()
                it.onSuccess(userAccount)
            } else {
                it.onError(Throwable("Not active account"))
            }
        }
    }

    override fun getPrepareAccountInfo(login: String, pass: String): Single<UserAccount> {
        return apiAccountRepo.getApiKey(login, pass)
                .subscribeOn(Schedulers.newThread())
                .flatMap {
                    val userAccount = accountConverter.fromApiToDomain(it.requestBody.account, pass)
                    userAccount.apiKey = it.sessionKey
                    Single.just(userAccount)
                }
                .doOnError {println(it.message)}
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addAccountPin(account: UserAccount): Completable {
        return Completable.create {
            val dbAccount = accountConverter.fromDomainToDb(account)
            dbAccount.isMain = true
            dbAccount.pin = BCrypt.hashpw(account.pin, BCrypt.gensalt())
            accountDao.insert(dbAccount)
            it.onComplete()
        }.doOnError {println(it.message) }
    }

    override fun getApiKey(userAccount: UserAccount): Single<String> {
        return apiAccountRepo.getApiKey(userAccount.login, userAccount.pass)
                .subscribeOn(Schedulers.newThread())
                .map { it.sessionKey }
                .doOnError { println(it.message) }
                .observeOn(AndroidSchedulers.mainThread())
    }
}