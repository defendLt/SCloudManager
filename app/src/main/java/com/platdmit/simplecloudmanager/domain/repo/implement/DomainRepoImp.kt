package com.platdmit.simplecloudmanager.domain.repo.implement

import com.platdmit.data.api.ApiDomainRepo
import com.platdmit.data.database.DbManager
import com.platdmit.data.database.dao.DomainDao
import com.platdmit.data.database.dao.DomainRecordDao
import com.platdmit.simplecloudmanager.domain.converters.DomainConverter
import com.platdmit.simplecloudmanager.domain.converters.DomainRecordConverter
import com.platdmit.simplecloudmanager.domain.helpers.UpdateScheduleService
import com.platdmit.simplecloudmanager.domain.models.Domain
import com.platdmit.simplecloudmanager.domain.repo.DomainBaseRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class DomainRepoImp : DomainBaseRepo {
    private val handCall = PublishSubject.create<Boolean>()
    private val mApiDomainRepo: ApiDomainRepo
    private val mDbDomainRepo: DomainDao
    private val mDomainConverter: DomainConverter
    private val mUpdateScheduleService: UpdateScheduleService

    private lateinit var mDbDomainRecordRepo: DomainRecordDao
    private lateinit var mDomainRecordConverter: DomainRecordConverter

    constructor(apiDomainRepo: ApiDomainRepo, dbManager: DbManager, domainConverter: DomainConverter, updateScheduleService: UpdateScheduleService) {
        mApiDomainRepo = apiDomainRepo
        mDbDomainRepo = dbManager.mDomainDao()
        mDomainConverter = domainConverter
        mUpdateScheduleService = updateScheduleService
    }

    constructor(apiDomainRepo: ApiDomainRepo, dbManager: DbManager, domainConverter: DomainConverter, domainRecordConverter: DomainRecordConverter, updateScheduleService: UpdateScheduleService) {
        mApiDomainRepo = apiDomainRepo
        mDbDomainRepo = dbManager.mDomainDao()
        mDbDomainRecordRepo = dbManager.mDomainRecordDao()
        mDomainConverter = domainConverter
        mDomainRecordConverter = domainRecordConverter
        mUpdateScheduleService = updateScheduleService
    }

    override fun getDomains(): Observable<List<Domain>> {
        return Observable.defer {
            if (mUpdateScheduleService.getActualStatus(TAG)) {
                return@defer Observable.just(mDbDomainRepo.getAllElement())
            } else {
                return@defer mApiDomainRepo.getDomains().flatMapObservable {
                    val dbDomains = mDomainConverter.fromApiToDbList(it)
                    mDbDomainRepo.insertList(dbDomains)
                    Observable.just(dbDomains)
                }.doOnComplete { mUpdateScheduleService.setDefaultUpdateTime(TAG) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap { Observable.just(mDomainConverter.fromDbToDomainList(it!!)) }
                .onErrorComplete {
                    println(it.message)
                    true
                }.repeatWhen { handCall.hide() }
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getDomain(id: Long): Observable<Domain> {
        return Observable.defer {
            if (mUpdateScheduleService.getActualStatus( TAG + id)) {
                return@defer Observable.just(mDbDomainRecordRepo.getRecordsForDomain(id))
            } else {
                return@defer mApiDomainRepo.getDomainRecords(id)
                        .flatMapObservable {
                            val recordsDb = mDomainRecordConverter.fromApiToDbList(it, id)
                            mDbDomainRecordRepo.insertList(recordsDb)
                            Observable.just(recordsDb)
                        }.doOnComplete { mUpdateScheduleService.setDefaultUpdateTime(TAG + id) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap {
                    val dbDomain = mDbDomainRepo.getElement(id)
                    val domain = mDomainConverter.fromDbToDomain(dbDomain!!)
                    domain.domainRecords = mDomainRecordConverter.fromDbToDomainList(it!!)
                    Observable.just(domain)
                }
                .onErrorComplete {
                    println(it.message)
                    true
                }
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun nextUpdate() {
        handCall.onNext(true)
    }

    companion object {
        private val TAG = DomainRepoImp::class.java.simpleName
    }
}