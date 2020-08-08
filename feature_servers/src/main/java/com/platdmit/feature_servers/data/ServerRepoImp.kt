package com.platdmit.feature_servers.data

import com.platdmit.base_utils.update_service.domain.UpdateScheduleService
import com.platdmit.feature_servers.domain.repositories.api.ApiServerRepo
import com.platdmit.mod_servers.data.retrofit.models.*
import com.platdmit.mod_servers.data.room.dao.*
import com.platdmit.mod_servers.data.room.entity.*
import com.platdmit.mod_servers.domain.converters.*
import com.platdmit.mod_servers.domain.models.*
import com.platdmit.mod_servers.domain.repositories.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class ServerRepoImp : _root_ide_package_.com.platdmit.feature_servers.domain.repositories.ServerBaseRepo, _root_ide_package_.com.platdmit.feature_servers.domain.repositories.ServerActionsRepo, _root_ide_package_.com.platdmit.feature_servers.domain.repositories.ServerStatisticsRepo, _root_ide_package_.com.platdmit.feature_servers.domain.repositories.ServerBackupRepo, _root_ide_package_.com.platdmit.feature_servers.domain.repositories.ServerLoadAveragesRepo {
    private val handCall = PublishSubject.create<Boolean>()
    private val apiServerRepo: _root_ide_package_.com.platdmit.feature_servers.domain.repositories.api.ApiServerRepo<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiServer, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage>
    private val updateScheduleService: UpdateScheduleService

    private lateinit var dbServerRepo: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.ServerDao
    private lateinit var dbActionRepo: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.ActionDao
    private lateinit var dbBackupRepo: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.BackupDao
    private lateinit var dbLoadAverageRepo: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.LoadAverageDao
    private lateinit var dbStatisticDao: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.StatisticDao
    private lateinit var serverConverter: _root_ide_package_.com.platdmit.feature_servers.domain.converters.ServerConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiServer, _root_ide_package_.com.platdmit.feature_servers.domain.models.Server, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbServer>
    private lateinit var actionConverter: _root_ide_package_.com.platdmit.feature_servers.domain.converters.ActionConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction, _root_ide_package_.com.platdmit.feature_servers.domain.models.Action, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbAction>
    private lateinit var backupConverter: _root_ide_package_.com.platdmit.feature_servers.domain.converters.BackupConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup, _root_ide_package_.com.platdmit.feature_servers.domain.models.Backup, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbBackup>
    private lateinit var statisticConverter: _root_ide_package_.com.platdmit.feature_servers.domain.converters.StatisticConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic, _root_ide_package_.com.platdmit.feature_servers.domain.models.Statistic, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbStatistic>
    private lateinit var loadAverageConverter: _root_ide_package_.com.platdmit.feature_servers.domain.converters.LoadAverageConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage, _root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage>

    private val TAG = _root_ide_package_.com.platdmit.feature_servers.data.ServerRepoImp::class.java.simpleName
    private val UPDATE_ACTION_TAG = "$TAG Acton"
    private val UPDATE_BACKUP_TAG = "$TAG Backup"
    private val UPDATE_LOAD_AVERAGE_TAG = "$TAG LoadAverage"
    private val UPDATE_STATISTICS_TAG = "$TAG Statistics"

    constructor(
            apiServerRepo: _root_ide_package_.com.platdmit.feature_servers.domain.repositories.api.ApiServerRepo<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiServer, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage>,
            serverDao: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.ServerDao,
            serverConverter: _root_ide_package_.com.platdmit.feature_servers.domain.converters.ServerConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiServer, _root_ide_package_.com.platdmit.feature_servers.domain.models.Server, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbServer>,
            updateScheduleService: UpdateScheduleService
    ) {
        this.apiServerRepo = apiServerRepo
        dbServerRepo = serverDao
        this.serverConverter = serverConverter
        this.updateScheduleService = updateScheduleService
    }

    constructor(
            apiServerRepo: _root_ide_package_.com.platdmit.feature_servers.domain.repositories.api.ApiServerRepo<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiServer, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage>,
            actionDao: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.ActionDao,
            actionConverter: _root_ide_package_.com.platdmit.feature_servers.domain.converters.ActionConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction, _root_ide_package_.com.platdmit.feature_servers.domain.models.Action, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbAction>,
            updateScheduleService: UpdateScheduleService
    ) {
        this.apiServerRepo = apiServerRepo
        dbActionRepo = actionDao
        this.actionConverter = actionConverter
        this.updateScheduleService = updateScheduleService
    }

    constructor(
            apiServerRepo: _root_ide_package_.com.platdmit.feature_servers.domain.repositories.api.ApiServerRepo<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiServer, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage>,
            backupDao: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.BackupDao,
            backupConverter: _root_ide_package_.com.platdmit.feature_servers.domain.converters.BackupConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup, _root_ide_package_.com.platdmit.feature_servers.domain.models.Backup, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbBackup>,
            updateScheduleService: UpdateScheduleService
    ) {
        this.apiServerRepo = apiServerRepo
        dbBackupRepo = backupDao
        this.backupConverter = backupConverter
        this.updateScheduleService = updateScheduleService
    }

    constructor(
            apiServerRepo: _root_ide_package_.com.platdmit.feature_servers.domain.repositories.api.ApiServerRepo<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiServer, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage>,
            loadAverageDao: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.LoadAverageDao,
            loadAverageConverter: _root_ide_package_.com.platdmit.feature_servers.domain.converters.LoadAverageConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage, _root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbLoadAverage>,
            updateScheduleService: UpdateScheduleService
    ) {
        this.apiServerRepo = apiServerRepo
        dbLoadAverageRepo = loadAverageDao
        this.loadAverageConverter = loadAverageConverter
        this.updateScheduleService = updateScheduleService
    }

    constructor(
            apiServerRepo: _root_ide_package_.com.platdmit.feature_servers.domain.repositories.api.ApiServerRepo<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiServer, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiAction, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiBackup, _root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiLoadAverage>,
            statisticDao: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.StatisticDao,
            statisticConverter: _root_ide_package_.com.platdmit.feature_servers.domain.converters.StatisticConverter<_root_ide_package_.com.platdmit.feature_servers.data.retrofit.models.ApiStatistic, _root_ide_package_.com.platdmit.feature_servers.domain.models.Statistic, _root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbStatistic>,
            updateScheduleService: UpdateScheduleService
    ) {
        this.apiServerRepo = apiServerRepo
        dbStatisticDao = statisticDao
        this.statisticConverter = statisticConverter
        this.updateScheduleService = updateScheduleService
    }

    override fun getServers(): Observable<List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Server>> {
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

    override fun getServer(id: Long): Single<_root_ide_package_.com.platdmit.feature_servers.domain.models.Server> {
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

    override fun getServerActions(id: Long): Observable<List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Action>> {
        return Observable.defer {
            if (updateScheduleService.getActualStatus(UPDATE_ACTION_TAG + id)) {
                Observable.just(dbActionRepo.getActionForServer(id))
            } else {
                apiServerRepo.getServerActions(id)
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

    override fun getServerBackups(id: Long): Observable<List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Backup>> {
        return Observable.defer {
            if (updateScheduleService.getActualStatus(UPDATE_BACKUP_TAG + id)) {
                 Observable.just(dbBackupRepo.getBackupsForServer(id))
            } else {
                 apiServerRepo.getServerBackups(id)
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

    override fun getServerLoadAverages(id: Long): Observable<List<_root_ide_package_.com.platdmit.feature_servers.domain.models.LoadAverage>> {
        return Observable.defer {
            if (updateScheduleService.getActualStatus(UPDATE_LOAD_AVERAGE_TAG + id)) {
                Observable.just(dbLoadAverageRepo.getLoadAverageForServer(id))
            } else {
                apiServerRepo.getServerLoadAverage(id)
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

    override fun getServerStatistics(id: Long): Observable<List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Statistic>> {
        return Observable.defer {
            if (updateScheduleService.getActualStatus(UPDATE_STATISTICS_TAG + id)) {
                Observable.just(dbStatisticDao.getStatisticsForServer(id))
            } else {
                apiServerRepo.getServerStatistics(id)
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

    private fun getBdOrApiServers(status: Boolean): Observable<List<_root_ide_package_.com.platdmit.feature_servers.data.room.entity.DbServer>> {
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