package com.platdmit.simplecloudmanager.domain.repo.implement;

import com.platdmit.simplecloudmanager.data.api.ApiDomainRepo;
import com.platdmit.simplecloudmanager.data.database.DbManager;
import com.platdmit.simplecloudmanager.data.database.dao.DomainDao;
import com.platdmit.simplecloudmanager.data.database.dao.DomainRecordDao;
import com.platdmit.simplecloudmanager.data.database.entity.DbDomain;
import com.platdmit.simplecloudmanager.data.database.entity.DbDomainRecord;
import com.platdmit.simplecloudmanager.domain.converters.DomainConverter;
import com.platdmit.simplecloudmanager.domain.converters.DomainRecordConverter;
import com.platdmit.simplecloudmanager.domain.helpers.UpdateScheduleService;
import com.platdmit.simplecloudmanager.domain.models.Domain;
import com.platdmit.simplecloudmanager.domain.repo.DomainBaseRepo;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class DomainRepoImp implements DomainBaseRepo {
    private static final String TAG = DomainRepoImp.class.getSimpleName();

    private PublishSubject<Boolean> handCall = PublishSubject.create();
    private List<DbDomain> mDbDomains;

    private ApiDomainRepo mApiDomainRepo;
    private DomainDao mDbDomainRepo;
    private DomainRecordDao mDbDomainRecordRepo;
    private DomainConverter mDomainConverter;
    private DomainRecordConverter mDomainRecordConverter;
    private UpdateScheduleService mUpdateScheduleService;

    public DomainRepoImp(ApiDomainRepo apiDomainRepo, DbManager dbManager, DomainConverter domainConverter, UpdateScheduleService updateScheduleService) {
        mApiDomainRepo = apiDomainRepo;
        mDbDomainRepo = dbManager.mDomainDao();
        mDomainConverter = domainConverter;
        mUpdateScheduleService = updateScheduleService;
    }

    public DomainRepoImp(ApiDomainRepo apiDomainRepo, DbManager dbManager, DomainConverter domainConverter, DomainRecordConverter domainRecordConverter, UpdateScheduleService updateScheduleService) {
        mApiDomainRepo = apiDomainRepo;
        mDbDomainRepo = dbManager.mDomainDao();
        mDbDomainRecordRepo = dbManager.mDomainRecordDao();
        mDomainConverter = domainConverter;
        mDomainRecordConverter = domainRecordConverter;
        mUpdateScheduleService = updateScheduleService;
    }

    @Override
    public Observable<List<Domain>> getDomains() {
        return Observable.defer(() -> {
            if (mUpdateScheduleService.getActualStatus(TAG)) {
                return Observable.just(mDbDomainRepo.getAll());
            } else {
                return mApiDomainRepo.getDomains().flatMapObservable(requestResult -> {
                    mDbDomains = mDomainConverter.fromApiToDbList(requestResult);
                    mDbDomainRepo.insertList(mDbDomains);
                    return Observable.just(mDbDomains);
                }).doOnComplete(() -> {
                    mUpdateScheduleService.setDefaultUpdateTime(TAG);
                });
            }
        })
                .subscribeOn(Schedulers.newThread())
                .flatMap(dbDomains -> Observable.just(mDomainConverter.fromDbToDomainList(dbDomains)))
                .onErrorComplete(throwable -> {
                    System.out.println(throwable.getMessage());
                    return true;
                }).repeatWhen(updateController -> handCall.hide())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Domain> getDomain(long id) {
        return Observable.defer(() -> {
            if (mUpdateScheduleService.getActualStatus(TAG + id)) {
                return Observable.just(mDbDomainRecordRepo.getRecordsForDomain(id));
            } else {
                return mApiDomainRepo.getDomainRecords(id)
                        .flatMapObservable(requestResult -> {
                            List<DbDomainRecord> recordsDb = mDomainRecordConverter.fromApiToDbList(requestResult, id);
                            mDbDomainRecordRepo.insertList(recordsDb);
                            return Observable.just(recordsDb);
                        }).doOnComplete(() -> {
                            mUpdateScheduleService.setDefaultUpdateTime(TAG + id);
                        });
            }
        })
                .subscribeOn(Schedulers.newThread())
                .flatMap(dbDomainRecords -> {
                    DbDomain dbDomain = mDbDomainRepo.getElement(id);
                    Domain domain = mDomainConverter.fromDbToDomain(dbDomain);
                    domain.setDomainRecords(mDomainRecordConverter.fromDbToDomainList(dbDomainRecords));
                    return Observable.just(domain);
                })
                .onErrorComplete(throwable -> {
                    System.out.println(throwable.getMessage());
                    return true;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void nextUpdate() {
        handCall.onNext(true);
    }
}
