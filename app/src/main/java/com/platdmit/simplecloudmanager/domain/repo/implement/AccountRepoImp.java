package com.platdmit.simplecloudmanager.domain.repo.implement;

import com.platdmit.simplecloudmanager.data.api.ApiAccountRepo;
import com.platdmit.simplecloudmanager.data.database.DbManager;
import com.platdmit.simplecloudmanager.data.database.dao.AccountDao;
import com.platdmit.simplecloudmanager.data.database.entity.DbAccount;
import com.platdmit.simplecloudmanager.domain.converters.AccountConverter;
import com.platdmit.simplecloudmanager.domain.models.UserAccount;
import com.platdmit.simplecloudmanager.domain.repo.AccountRepo;

import org.mindrot.BCrypt;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AccountRepoImp implements AccountRepo {
    private static final String TAG = DomainRepoImp.class.getSimpleName();

    private ApiAccountRepo mApiAccountRepo;
    private AccountDao mDbAccountRepo;
    private AccountConverter mAccountConverter;

    public AccountRepoImp(ApiAccountRepo apiAccountRepo, DbManager dbManager, AccountConverter accountConverter) {
        mApiAccountRepo = apiAccountRepo;
        mDbAccountRepo = dbManager.mAccountDao();
        mAccountConverter = accountConverter;
    }

    @Override
    public Single<UserAccount> getActiveAccount(){
        return Single.create(account -> {
            DbAccount dbAccount = mDbAccountRepo.getBaseAccount();
            if(dbAccount != null){
                UserAccount userAccount = mAccountConverter.fromDbToDomain(dbAccount);
                userAccount.setPin(dbAccount.getPin());
                account.onSuccess(userAccount);
            } else {
                account.onError(new Throwable("Not active account"));
            }
        });
    }


    public void getApiKeyForAccount(String login, String pass) {
        new Thread(() -> {
//            ApiRequestResult<ApiAuth> apiRequestResult = mApiAccountRepo.getApiKey(login, pass);
//            if(apiRequestResult.getStatus()){
//                String apiKey = apiRequestResult.getResult().getSessionKey();
//                if(apiKey != null){
//                    mActiveApiKey.postValue(apiKey);
//                }
//            } else {
//                mResultMassage.postValue(apiRequestResult.getMassage());
//            }
        }).start();
    }

    @Override
    public Single<UserAccount> getPrepareAccountInfo(String login, String pass) {
        return mApiAccountRepo.getApiKey(login, pass)
                .subscribeOn(Schedulers.newThread())
                .flatMap(requestResult -> {
                    String apiKey = requestResult.getSessionKey();
                    UserAccount userAccount = mAccountConverter.fromApiToDomain(requestResult.getAccount().getAccount(), pass);
                    userAccount.setApiKey(apiKey);
                    return Single.just(userAccount);
                })
                .doOnError(throwable -> {
                    System.out.println(throwable.getMessage());
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable addAccountPin(UserAccount account) {
        return Completable.create(addPin -> {
                    DbAccount dbAccount = mAccountConverter.fromDomainToDb(account);
                    dbAccount.setMain(true);
                    dbAccount.setPin(BCrypt.hashpw(String.valueOf(account.getPin()), BCrypt.gensalt()));
                    mDbAccountRepo.insert(dbAccount);
                    addPin.onComplete();
                }).doOnError(throwable -> {
                    System.out.println(throwable.getMessage());
                });
    }
}
