package com.platdmit.mod_domains.data

import com.platdmit.mod_domains.domain.converters.DomainConverter
import com.platdmit.mod_domains.domain.converters.DomainRecordConverter
import com.platdmit.domain.helpers.UpdateScheduleService
import com.platdmit.mod_domains.domain.models.Domain
import com.platdmit.mod_domains.domain.models.DomainRecord
import com.platdmit.mod_domains.domain.repositories.DomainBaseRepo
import com.platdmit.mod_domains.domain.repositories.api.ApiDomainRepo
import com.platdmit.mod_domains.data.retrofit.models.ApiDomain
import com.platdmit.mod_domains.data.retrofit.models.ApiDomainRecord
import com.platdmit.mod_domains.data.room.dao.DomainDao
import com.platdmit.mod_domains.data.room.entity.DbDomain
import com.platdmit.mod_domains.data.room.entity.DbDomainRecord
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class DomainRepoImp
    constructor(
            private val apiDomainRepo: ApiDomainRepo<ApiDomain, ApiDomainRecord>,
            private val domainDao: DomainDao,
            private val domainConverter: DomainConverter<ApiDomain, Domain, DbDomain>,
            private val updateScheduleService: UpdateScheduleService,
            private val domainRecordDao: com.platdmit.mod_domains.data.room.dao.DomainRecordDao? = null,
            private val domainRecordConverter: DomainRecordConverter<ApiDomainRecord, DomainRecord, DbDomainRecord>? = null
    ) : DomainBaseRepo {

    private val TAG = DomainRepoImp::class.java.simpleName
    private val handCall = PublishSubject.create<Boolean>()

    override fun getDomains(): Observable<List<Domain>> {
        return Observable.defer {
            if (updateScheduleService.getActualStatus(TAG)) {
                return@defer Observable.just(domainDao.getAllElement())
            } else {
                return@defer apiDomainRepo.getDomains().flatMapObservable {
                    val dbDomains = domainConverter.fromApiToDbList(it)
                    domainDao.insertList(dbDomains)
                    Observable.just(dbDomains)
                }.doOnComplete { updateScheduleService.setDefaultUpdateTime(TAG) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap { Observable.just(domainConverter.fromDbToDomainList(it!!)) }
                .onErrorComplete {
                    println(it.message)
                    true
                }.repeatWhen { handCall.hide() }
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getDomain(id: Long): Observable<Domain> {
        return Observable.defer {
            if (updateScheduleService.getActualStatus( TAG + id)) {
                return@defer Observable.just(domainRecordDao?.getRecordsForDomain(id))
            } else {
                return@defer apiDomainRepo.getDomainRecords(id)
                        .flatMapObservable {
                            val recordsDb = domainRecordConverter?.fromApiToDbList(it, id)
                            recordsDb?.let { record ->
                                domainRecordDao?.insertList(record)
                                Observable.just(record)
                            }
                        }.doOnComplete { updateScheduleService.setDefaultUpdateTime(TAG + id) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap {
                    val dbDomain = domainDao.getElement(id)
                    val domain = domainConverter.fromDbToDomain(dbDomain!!)
                    domain.domainRecords = domainRecordConverter?.fromDbToDomainList(it!!)
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
}