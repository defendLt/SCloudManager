package com.platdmit.simplecloudmanager.domain.repo.implement;

import com.platdmit.simplecloudmanager.data.api.ApiServerRepo;
import com.platdmit.simplecloudmanager.data.database.DbManager;
import com.platdmit.simplecloudmanager.data.database.dao.ActionDao;
import com.platdmit.simplecloudmanager.data.database.dao.BackupDao;
import com.platdmit.simplecloudmanager.data.database.dao.LoadAverageDao;
import com.platdmit.simplecloudmanager.data.database.dao.ServerDao;
import com.platdmit.simplecloudmanager.data.database.dao.StatisticDao;
import com.platdmit.simplecloudmanager.data.database.entity.DbAction;
import com.platdmit.simplecloudmanager.data.database.entity.DbBackup;
import com.platdmit.simplecloudmanager.data.database.entity.DbLoadAverage;
import com.platdmit.simplecloudmanager.data.database.entity.DbServer;
import com.platdmit.simplecloudmanager.data.database.entity.DbStatistic;
import com.platdmit.simplecloudmanager.domain.converters.ActionConverter;
import com.platdmit.simplecloudmanager.domain.converters.BackupConverter;
import com.platdmit.simplecloudmanager.domain.converters.LoadAverageConverter;
import com.platdmit.simplecloudmanager.domain.converters.ServerConverter;
import com.platdmit.simplecloudmanager.domain.converters.StatisticConverter;
import com.platdmit.simplecloudmanager.domain.helpers.UpdateScheduleService;
import com.platdmit.simplecloudmanager.domain.models.Action;
import com.platdmit.simplecloudmanager.domain.models.Backup;
import com.platdmit.simplecloudmanager.domain.models.LoadAverage;
import com.platdmit.simplecloudmanager.domain.models.Server;
import com.platdmit.simplecloudmanager.domain.models.Statistic;
import com.platdmit.simplecloudmanager.domain.repo.ServerActionsRepo;
import com.platdmit.simplecloudmanager.domain.repo.ServerBackupRepo;
import com.platdmit.simplecloudmanager.domain.repo.ServerBaseRepo;
import com.platdmit.simplecloudmanager.domain.repo.ServerLoadAveragesRepo;
import com.platdmit.simplecloudmanager.domain.repo.ServerStatisticsRepo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class ServerRepoImp implements
        ServerBaseRepo,
        ServerActionsRepo,
        ServerStatisticsRepo,
        ServerBackupRepo,
        ServerLoadAveragesRepo {
    private static final String TAG = ServerRepoImp.class.getSimpleName();
    private static final String UPDATE_ACTION_TAG = ServerRepoImp.class.getSimpleName()+"Acton";
    private static final String UPDATE_BACKUP_TAG = ServerRepoImp.class.getSimpleName()+"Backup";
    private static final String UPDATE_LOAD_AVERAGE_TAG = ServerRepoImp.class.getSimpleName()+"LoadAverage";
    private static final String UPDATE_STATISTICS_TAG = ServerRepoImp.class.getSimpleName()+"Statistics";

    private PublishSubject<Boolean> handCall = PublishSubject.create();
    private List<DbServer> mDbServers;

    private ApiServerRepo mApiServerRepo;
    private ServerDao mDbServerRepo;
    private ActionDao mDbActionRepo;
    private BackupDao mDbBackupRepo;
    private LoadAverageDao mDbLoadAverageRepo;
    private StatisticDao mDbStatisticDao;
    private ServerConverter mServerConverter;
    private ActionConverter mActionConverter;
    private BackupConverter mBackupConverter;
    private StatisticConverter mStatisticConverter;
    private LoadAverageConverter mLoadAverageConverter;
    private UpdateScheduleService mUpdateScheduleService;

    public ServerRepoImp(ApiServerRepo apiServerRepo, DbManager dbManager, ServerConverter serverConverter, UpdateScheduleService updateScheduleService) {
        mApiServerRepo = apiServerRepo;
        mDbServerRepo = dbManager.mServerDao();
        mServerConverter = serverConverter;
        mUpdateScheduleService = updateScheduleService;
    }

    public ServerRepoImp(ApiServerRepo apiServerRepo, DbManager dbManager, ActionConverter actionConverter, UpdateScheduleService updateScheduleService) {
        mApiServerRepo = apiServerRepo;
        mDbActionRepo = dbManager.mActionDao();
        mActionConverter = actionConverter;
        mUpdateScheduleService = updateScheduleService;
    }

    public ServerRepoImp(ApiServerRepo apiServerRepo, DbManager dbManager, BackupConverter backupConverter, UpdateScheduleService updateScheduleService) {
        mApiServerRepo = apiServerRepo;
        mDbBackupRepo = dbManager.mBackupDao();
        mBackupConverter = backupConverter;
        mUpdateScheduleService = updateScheduleService;
    }

    public ServerRepoImp(ApiServerRepo apiServerRepo, DbManager dbManager, LoadAverageConverter loadAverageConverter, UpdateScheduleService updateScheduleService) {
        mApiServerRepo = apiServerRepo;
        mDbLoadAverageRepo = dbManager.mLoadAverageDao();
        mLoadAverageConverter = loadAverageConverter;
        mUpdateScheduleService = updateScheduleService;
    }

    public ServerRepoImp(ApiServerRepo apiServerRepo, DbManager dbManager, StatisticConverter statisticConverter, UpdateScheduleService updateScheduleService) {
        mApiServerRepo = apiServerRepo;
        mDbStatisticDao = dbManager.mStatisticDao();
        mStatisticConverter = statisticConverter;
        mUpdateScheduleService = updateScheduleService;
    }


    @Override
    public Observable<List<Server>> getServers() {
        return Observable.defer(() -> getBdOrApiServers(mUpdateScheduleService.getActualStatus(TAG)))
                .subscribeOn(Schedulers.newThread())
                .flatMap(servers -> Observable.just(mServerConverter.fromDbToDomainList(servers)))
                .onErrorComplete(throwable -> {
                    System.out.println(throwable.getMessage());
                    return true;
                })
                .repeatWhen(updateController -> handCall.hide())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<Server> getServer(long id) {
        return Single.create(emitter -> {
            try {
                DbServer dbServer = mDbServerRepo.getElement(id);
                if (dbServer != null) {
                    emitter.onSuccess(mServerConverter.fromDbToDomainFull(dbServer));
                } else {
                    throw new Throwable("Not search element");
                }
            } catch (Exception e){
                emitter.onError(e);
            }
        });
    }

    @Override
    public void nextUpdate() {
        handCall.onNext(true);
    }

    @Override
    public Observable<List<Action>> getServerActions(long id) {
        return Observable.defer(() -> {
            if (mUpdateScheduleService.getActualStatus(UPDATE_ACTION_TAG + id)) {
                return Observable.just(mDbActionRepo.getActionForServer(id));
            } else {
                return mApiServerRepo.getServerActions(id)
                        .flatMapObservable(requestResult -> {
                            List<DbAction> dbActions = mActionConverter.fromApiToDbList(requestResult);
                            mDbActionRepo.insertList(dbActions);
                            return Observable.just(dbActions);
                        }).doOnComplete(() -> {
                            mUpdateScheduleService.setUpdateTime(UPDATE_ACTION_TAG + id, 300000);
                        });
            }
        })
                .subscribeOn(Schedulers.newThread())
                .flatMap(dbActionList -> Observable.just(mActionConverter.fromDbToDomainList(dbActionList)))
                .onErrorComplete(throwable -> {
                    System.out.println(throwable.getMessage());
                    return true;
                })
                .repeatWhen(updateController -> updateController.delay(5, TimeUnit.MINUTES))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Backup>> getServerBackups(long id) {
        return Observable.defer(() -> {
            if (mUpdateScheduleService.getActualStatus(UPDATE_BACKUP_TAG + id)) {
                return Observable.just(mDbBackupRepo.getBackupsForServer(id));
            } else {
                return mApiServerRepo.getServerBackups(id)
                        .flatMapObservable(requestResult -> {
                            List<DbBackup> dbBackups = mBackupConverter.fromApiToDbList(requestResult, id);
                            mDbBackupRepo.insertList(dbBackups);
                            return Observable.just(dbBackups);
                        })
                        .doOnComplete(() -> {
                            mUpdateScheduleService.setUpdateTime(UPDATE_ACTION_TAG + id, 450000);
                        });
            }
        })
                .subscribeOn(Schedulers.newThread())
                .flatMap(dbBackups -> Observable.just(mBackupConverter.fromDbToDomainList(dbBackups)))
                .onErrorComplete(throwable -> {
                    System.out.println(throwable.getMessage());
                    return true;
                })
                .repeatWhen(updateController -> updateController.delay(10, TimeUnit.MINUTES))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<LoadAverage>> getServerLoadAverages(long id) {
        return Observable.defer(() -> {
            if (mUpdateScheduleService.getActualStatus(UPDATE_LOAD_AVERAGE_TAG + id)) {
                return Observable.just(mDbLoadAverageRepo.getLoadAverageForServer(id));
            } else {
                return mApiServerRepo.getServerLoadAverage(id)
                        .flatMapObservable(requestResult -> {
                            List<DbLoadAverage> dbLoadAverages = mLoadAverageConverter.fromApiToDb(requestResult, id);
                            mDbLoadAverageRepo.insertList(dbLoadAverages);
                            return Observable.just(dbLoadAverages);
                        }).doOnComplete(() -> {
                            mUpdateScheduleService.setUpdateTime(UPDATE_LOAD_AVERAGE_TAG + id, 45000);
                        });
            }
        })
                .subscribeOn(Schedulers.newThread())
                .flatMap(dbLoadAverages -> Observable.just(mLoadAverageConverter.fromDbToDomain(dbLoadAverages)))
                .onErrorComplete(throwable -> {
                    System.out.println(throwable.getMessage());
                    return true;
                })
                .repeatWhen(updateController -> updateController.delay(1, TimeUnit.MINUTES))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Statistic>> getServerStatistics(long id) {
        return Observable.defer(() -> {
            if (mUpdateScheduleService.getActualStatus(UPDATE_STATISTICS_TAG + id)) {
                return Observable.just(mDbStatisticDao.getStatisticsForServer(id));
            } else {
                return mApiServerRepo.getServerStatistics(id)
                        .flatMapObservable(requestResult -> {
                            List<DbStatistic> dbStatistics = mStatisticConverter.fromApiToDbList(requestResult, id);
                            mDbStatisticDao.deleteAll(id);
                            mDbStatisticDao.insertList(dbStatistics);
                            return Observable.just(dbStatistics);
                        }).doOnComplete(() -> {
                            mUpdateScheduleService.setUpdateTime(UPDATE_STATISTICS_TAG + id, 45000);
                        });
            }
        })
                .subscribeOn(Schedulers.newThread())
                .flatMap(dbStatistics -> Observable.just(mStatisticConverter.fromDbToDomainList(dbStatistics)))
                .repeatWhen(updateController -> updateController.delay(1, TimeUnit.MINUTES))
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<DbServer>> getBdOrApiServers(boolean status){
        if (status) {
            return Observable.just(mDbServerRepo.getAll());
        } else {
            return mApiServerRepo.getServers()
                    .flatMapObservable(requestResult -> {
                        mDbServers = mServerConverter.fromApiToDbList(requestResult);
                        mDbServerRepo.insertList(mDbServers);
                        return Observable.just(mDbServers);
                    }).doOnComplete(() -> {
                        mUpdateScheduleService.setDefaultUpdateTime(TAG);
                    });
        }
    }
}
