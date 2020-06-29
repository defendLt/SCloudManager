package com.platdmit.simplecloudmanager.domain.repo.implement

import com.platdmit.simplecloudmanager.data.api.ApiServerRepo
import com.platdmit.simplecloudmanager.data.database.DbManager
import com.platdmit.simplecloudmanager.data.database.dao.*
import com.platdmit.simplecloudmanager.data.database.entity.DbServer
import com.platdmit.simplecloudmanager.domain.converters.*
import com.platdmit.simplecloudmanager.domain.helpers.UpdateScheduleService
import com.platdmit.simplecloudmanager.domain.models.*
import com.platdmit.simplecloudmanager.domain.repo.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class ServerRepoImp : ServerBaseRepo, ServerActionsRepo, ServerStatisticsRepo, ServerBackupRepo, ServerLoadAveragesRepo {
    private val handCall = PublishSubject.create<Boolean>()
    private val mApiServerRepo: ApiServerRepo
    private val mUpdateScheduleService: UpdateScheduleService

    private lateinit var mDbServerRepo: ServerDao
    private lateinit var mDbActionRepo: ActionDao
    private lateinit var mDbBackupRepo: BackupDao
    private lateinit var mDbLoadAverageRepo: LoadAverageDao
    private lateinit var mDbStatisticDao: StatisticDao
    private lateinit var mServerConverter: ServerConverter
    private lateinit var mActionConverter: ActionConverter
    private lateinit var mBackupConverter: BackupConverter
    private lateinit var mStatisticConverter: StatisticConverter
    private lateinit var mLoadAverageConverter: LoadAverageConverter

    constructor(apiServerRepo: ApiServerRepo, dbManager: DbManager, serverConverter: ServerConverter, updateScheduleService: UpdateScheduleService) {
        mApiServerRepo = apiServerRepo
        mDbServerRepo = dbManager.mServerDao()
        mServerConverter = serverConverter
        mUpdateScheduleService = updateScheduleService
    }

    constructor(apiServerRepo: ApiServerRepo, dbManager: DbManager, actionConverter: ActionConverter, updateScheduleService: UpdateScheduleService) {
        mApiServerRepo = apiServerRepo
        mDbActionRepo = dbManager.mActionDao()
        mActionConverter = actionConverter
        mUpdateScheduleService = updateScheduleService
    }

    constructor(apiServerRepo: ApiServerRepo, dbManager: DbManager, backupConverter: BackupConverter, updateScheduleService: UpdateScheduleService) {
        mApiServerRepo = apiServerRepo
        mDbBackupRepo = dbManager.mBackupDao()
        mBackupConverter = backupConverter
        mUpdateScheduleService = updateScheduleService
    }

    constructor(apiServerRepo: ApiServerRepo, dbManager: DbManager, loadAverageConverter: LoadAverageConverter, updateScheduleService: UpdateScheduleService) {
        mApiServerRepo = apiServerRepo
        mDbLoadAverageRepo = dbManager.mLoadAverageDao()
        mLoadAverageConverter = loadAverageConverter
        mUpdateScheduleService = updateScheduleService
    }

    constructor(apiServerRepo: ApiServerRepo, dbManager: DbManager, statisticConverter: StatisticConverter, updateScheduleService: UpdateScheduleService) {
        mApiServerRepo = apiServerRepo
        mDbStatisticDao = dbManager.mStatisticDao()
        mStatisticConverter = statisticConverter
        mUpdateScheduleService = updateScheduleService
    }

    override fun getServers(): Observable<List<Server>> {
        return Observable.defer { getBdOrApiServers(mUpdateScheduleService.getActualStatus(TAG)) }
                .subscribeOn(Schedulers.newThread())
                .flatMap {Observable.just(mServerConverter.fromDbToDomainList(it))}
                .onErrorComplete {
                    println(it.message)
                    true
                }
                .repeatWhen { handCall.hide() }
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getServer(id: Long): Single<Server> {
        return Single.create {
            try {
                val dbServer = mDbServerRepo.getElement(id)
                if (dbServer != null) {
                    it.onSuccess(mServerConverter.fromDbToDomainFull(dbServer))
                } else {
                    throw Throwable("Not search element")
                }
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    override fun getServerActions(id: Long): Observable<List<Action>> {
        return Observable.defer {
            if (mUpdateScheduleService.getActualStatus(UPDATE_ACTION_TAG + id)) {
                return@defer Observable.just(mDbActionRepo.getActionForServer(id))
            } else {
                return@defer mApiServerRepo.getServerActions(id)
                        .flatMapObservable {
                            val dbActions = mActionConverter.fromApiToDbList(it)
                            mDbActionRepo.insertList(dbActions)
                            Observable.just(dbActions)
                        }.doOnComplete { mUpdateScheduleService.setUpdateTime(UPDATE_ACTION_TAG + id, 300000) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap {Observable.just(mActionConverter.fromDbToDomainList(it))}
                .onErrorComplete {
                    println(it.message)
                    true
                }
                .repeatWhen {it.delay(5, TimeUnit.MINUTES)}
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getServerBackups(id: Long): Observable<List<Backup>> {
        return Observable.defer {
            if (mUpdateScheduleService.getActualStatus(UPDATE_BACKUP_TAG + id)) {
                return@defer Observable.just(mDbBackupRepo.getBackupsForServer(id))
            } else {
                return@defer mApiServerRepo.getServerBackups(id)
                        .flatMapObservable {
                            val dbBackups = mBackupConverter.fromApiToDbList(it, id)
                            mDbBackupRepo.insertList(dbBackups)
                            Observable.just(dbBackups)
                        }
                        .doOnComplete { mUpdateScheduleService.setUpdateTime(UPDATE_ACTION_TAG + id, 450000) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap {Observable.just(mBackupConverter.fromDbToDomainList(it)) }
                .onErrorComplete {
                    println(it.message)
                    true
                }
                .repeatWhen {it.delay(10, TimeUnit.MINUTES)}
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getServerLoadAverages(id: Long): Observable<List<LoadAverage>> {
        return Observable.defer {
            if (mUpdateScheduleService.getActualStatus(UPDATE_LOAD_AVERAGE_TAG + id)) {
                return@defer Observable.just(mDbLoadAverageRepo.getLoadAverageForServer(id))
            } else {
                return@defer mApiServerRepo.getServerLoadAverage(id)
                        .flatMapObservable {
                            val dbLoadAverages = mLoadAverageConverter.fromApiToDb(it, id)
                            mDbLoadAverageRepo.insertList(dbLoadAverages)
                            Observable.just(dbLoadAverages)
                        }.doOnComplete { mUpdateScheduleService.setUpdateTime(UPDATE_LOAD_AVERAGE_TAG + id, 45000) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap {Observable.just(mLoadAverageConverter.fromDbToDomain(it))}
                .onErrorComplete {
                    println(it.message)
                    true
                }
                .repeatWhen {it.delay(1, TimeUnit.MINUTES)}
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getServerStatistics(id: Long): Observable<List<Statistic>> {
        return Observable.defer {
            if (mUpdateScheduleService.getActualStatus(UPDATE_STATISTICS_TAG + id)) {
                return@defer Observable.just(mDbStatisticDao.getStatisticsForServer(id))
            } else {
                return@defer mApiServerRepo.getServerStatistics(id)
                        .flatMapObservable {
                            val dbStatistics = mStatisticConverter.fromApiToDbList(it, id)
                            mDbStatisticDao.deleteAll(id)
                            mDbStatisticDao.insertList(dbStatistics)
                            Observable.just(dbStatistics)
                        }.doOnComplete { mUpdateScheduleService.setUpdateTime(UPDATE_STATISTICS_TAG + id, 45000) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap {Observable.just(mStatisticConverter.fromDbToDomainList(it))}
                .repeatWhen {it.delay(1, TimeUnit.MINUTES)}
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun nextUpdate() {
        handCall.onNext(true)
    }

    private fun getBdOrApiServers(status: Boolean): Observable<List<DbServer>> {
        return if (status) {
            Observable.just(mDbServerRepo.all)
        } else {
            mApiServerRepo.getServers()
                    .flatMapObservable {
                        val mDbServers = mServerConverter.fromApiToDbList(it)
                        mDbServerRepo.insertList(mDbServers)
                        Observable.just(mDbServers)
                    }.doOnComplete { mUpdateScheduleService.setDefaultUpdateTime(TAG) }
        }
    }

    companion object {
        private val TAG = ServerRepoImp::class.java.simpleName
        private val UPDATE_ACTION_TAG = "$TAG Acton"
        private val UPDATE_BACKUP_TAG = "$TAG Backup"
        private val UPDATE_LOAD_AVERAGE_TAG = "$TAG LoadAverage"
        private val UPDATE_STATISTICS_TAG = "$TAG Statistics"
    }
}