package com.platdmit.domain.repo.implement

import com.platdmit.data.api.ApiServerRepo
import com.platdmit.data.api.models.*
import com.platdmit.data.database.DbManager
import com.platdmit.data.database.dao.*
import com.platdmit.data.database.entity.*
import com.platdmit.domain.converters.*
import com.platdmit.domain.helpers.UpdateScheduleService
import com.platdmit.domain.models.*
import com.platdmit.domain.repo.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class ServerRepoImp : ServerBaseRepo, ServerActionsRepo, ServerStatisticsRepo, ServerBackupRepo, ServerLoadAveragesRepo {
    private val handCall = PublishSubject.create<Boolean>()
    private val apiServerRepo: ApiServerRepo
    private val updateScheduleService: UpdateScheduleService

    private lateinit var dbServerRepo: ServerDao
    private lateinit var dbActionRepo: ActionDao
    private lateinit var dbBackupRepo: BackupDao
    private lateinit var dbLoadAverageRepo: LoadAverageDao
    private lateinit var dbStatisticDao: StatisticDao
    private lateinit var serverConverter: ServerConverter<ApiServer, Server, DbServer>
    private lateinit var actionConverter: ActionConverter<ApiAction, Action, DbAction>
    private lateinit var backupConverter: BackupConverter<ApiBackup, Backup, DbBackup>
    private lateinit var statisticConverter: StatisticConverter<ApiStatistic, Statistic, DbStatistic>
    private lateinit var loadAverageConverter: LoadAverageConverter<ApiLoadAverage, LoadAverage, DbLoadAverage>

    private val TAG = ServerRepoImp::class.java.simpleName
    private val UPDATE_ACTION_TAG = "$TAG Acton"
    private val UPDATE_BACKUP_TAG = "$TAG Backup"
    private val UPDATE_LOAD_AVERAGE_TAG = "$TAG LoadAverage"
    private val UPDATE_STATISTICS_TAG = "$TAG Statistics"

    constructor(
            apiServerRepo: ApiServerRepo,
            serverDao: ServerDao,
            serverConverter: ServerConverter<ApiServer, Server, DbServer>,
            updateScheduleService: UpdateScheduleService
    ) {
        this.apiServerRepo = apiServerRepo
        dbServerRepo = serverDao
        this.serverConverter = serverConverter
        this.updateScheduleService = updateScheduleService
    }

    constructor(
            apiServerRepo: ApiServerRepo,
            actionDao: ActionDao,
            actionConverter: ActionConverter<ApiAction, Action, DbAction>,
            updateScheduleService: UpdateScheduleService
    ) {
        this.apiServerRepo = apiServerRepo
        dbActionRepo = actionDao
        this.actionConverter = actionConverter
        this.updateScheduleService = updateScheduleService
    }

    constructor(
            apiServerRepo: ApiServerRepo,
            backupDao: BackupDao,
            backupConverter: BackupConverter<ApiBackup, Backup, DbBackup>,
            updateScheduleService: UpdateScheduleService
    ) {
        this.apiServerRepo = apiServerRepo
        dbBackupRepo = backupDao
        this.backupConverter = backupConverter
        this.updateScheduleService = updateScheduleService
    }

    constructor(
            apiServerRepo: ApiServerRepo,
            loadAverageDao: LoadAverageDao,
            loadAverageConverter: LoadAverageConverter<ApiLoadAverage, LoadAverage, DbLoadAverage>,
            updateScheduleService: UpdateScheduleService
    ) {
        this.apiServerRepo = apiServerRepo
        dbLoadAverageRepo = loadAverageDao
        this.loadAverageConverter = loadAverageConverter
        this.updateScheduleService = updateScheduleService
    }

    constructor(
            apiServerRepo: ApiServerRepo,
            statisticDao: StatisticDao,
            statisticConverter: StatisticConverter<ApiStatistic, Statistic, DbStatistic>,
            updateScheduleService: UpdateScheduleService
    ) {
        this.apiServerRepo = apiServerRepo
        dbStatisticDao = statisticDao
        this.statisticConverter = statisticConverter
        this.updateScheduleService = updateScheduleService
    }

    override fun getServers(): Observable<List<Server>> {
        return Observable.defer { getBdOrApiServers(updateScheduleService.getActualStatus(TAG)) }
                .subscribeOn(Schedulers.newThread())
                .flatMap {Observable.just(serverConverter.fromDbToDomainList(it))}
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
                val dbServer = dbServerRepo.getElement(id)
                if (dbServer != null) {
                    it.onSuccess(serverConverter.fromDbToDomainFull(dbServer))
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
            if (updateScheduleService.getActualStatus(UPDATE_ACTION_TAG + id)) {
                return@defer Observable.just(dbActionRepo.getActionForServer(id))
            } else {
                return@defer apiServerRepo.getServerActions(id)
                        .flatMapObservable {
                            val dbActions = actionConverter.fromApiToDbList(it)
                            dbActionRepo.insertList(dbActions)
                            Observable.just(dbActions)
                        }.doOnComplete { updateScheduleService.setUpdateTime(UPDATE_ACTION_TAG + id, 300000) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap {Observable.just(actionConverter.fromDbToDomainList(it!!))}
                .onErrorComplete {
                    println(it.message)
                    true
                }
                .repeatWhen {it.delay(5, TimeUnit.MINUTES)}
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getServerBackups(id: Long): Observable<List<Backup>> {
        return Observable.defer {
            if (updateScheduleService.getActualStatus(UPDATE_BACKUP_TAG + id)) {
                return@defer Observable.just(dbBackupRepo.getBackupsForServer(id))
            } else {
                return@defer apiServerRepo.getServerBackups(id)
                        .flatMapObservable {
                            val dbBackups = backupConverter.fromApiToDbList(it, id)
                            dbBackupRepo.insertList(dbBackups)
                            Observable.just(dbBackups)
                        }
                        .doOnComplete { updateScheduleService.setUpdateTime(UPDATE_ACTION_TAG + id, 450000) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap {Observable.just(backupConverter.fromDbToDomainList(it!!)) }
                .onErrorComplete {
                    println(it.message)
                    true
                }
                .repeatWhen {it.delay(10, TimeUnit.MINUTES)}
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getServerLoadAverages(id: Long): Observable<List<LoadAverage>> {
        return Observable.defer {
            if (updateScheduleService.getActualStatus(UPDATE_LOAD_AVERAGE_TAG + id)) {
                return@defer Observable.just(dbLoadAverageRepo.getLoadAverageForServer(id))
            } else {
                return@defer apiServerRepo.getServerLoadAverage(id)
                        .flatMapObservable {
                            val dbLoadAverages = loadAverageConverter.fromApiToDb(it, id)
                            dbLoadAverageRepo.insertList(dbLoadAverages)
                            Observable.just(dbLoadAverages)
                        }.doOnComplete { updateScheduleService.setUpdateTime(UPDATE_LOAD_AVERAGE_TAG + id, 45000) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap {Observable.just(loadAverageConverter.fromDbToDomain(it!!))}
                .onErrorComplete {
                    println(it.message)
                    true
                }
                .repeatWhen {it.delay(1, TimeUnit.MINUTES)}
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getServerStatistics(id: Long): Observable<List<Statistic>> {
        return Observable.defer {
            if (updateScheduleService.getActualStatus(UPDATE_STATISTICS_TAG + id)) {
                return@defer Observable.just(dbStatisticDao.getStatisticsForServer(id))
            } else {
                return@defer apiServerRepo.getServerStatistics(id)
                        .flatMapObservable {
                            val dbStatistics = statisticConverter.fromApiToDbList(it, id)
                            dbStatisticDao.deleteAll(id)
                            dbStatisticDao.insertList(dbStatistics)
                            Observable.just(dbStatistics)
                        }.doOnComplete { updateScheduleService.setUpdateTime(UPDATE_STATISTICS_TAG + id, 45000) }
            }
        }
                .subscribeOn(Schedulers.newThread())
                .flatMap {Observable.just(statisticConverter.fromDbToDomainList(it!!))}
                .onErrorComplete {
                    println(it.message)
                    true
                }
                .repeatWhen {it.delay(1, TimeUnit.MINUTES)}
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun nextUpdate() {
        handCall.onNext(true)
    }

    private fun getBdOrApiServers(status: Boolean): Observable<List<DbServer>> {
        return if (status) {
            Observable.just(dbServerRepo.getAllElement())
        } else {
            apiServerRepo.getServers()
                    .flatMapObservable {
                        val mDbServers = serverConverter.fromApiToDbList(it)
                        dbServerRepo.insertList(mDbServers)
                        Observable.just(mDbServers)
                    }.doOnComplete { updateScheduleService.setDefaultUpdateTime(TAG) }
        }
    }
}