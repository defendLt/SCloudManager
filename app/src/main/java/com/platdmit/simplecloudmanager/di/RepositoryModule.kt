package com.platdmit.simplecloudmanager.di

import com.platdmit.data.api.ApiAccountRepo
import com.platdmit.data.api.ApiDomainRepo
import com.platdmit.data.api.ApiServerRepo
import com.platdmit.data.database.dao.*
import com.platdmit.domain.converters.implement.*
import com.platdmit.domain.helpers.UpdateScheduleService
import com.platdmit.domain.repo.*
import com.platdmit.domain.repo.implement.AccountRepoImp
import com.platdmit.domain.repo.implement.DomainRepoImp
import com.platdmit.domain.repo.implement.ServerRepoImp
import com.platdmit.domain.repo.implement.UpdateScheduleRepImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideAccountRepo(
            apiAccountRepo: ApiAccountRepo,
            accountDao: AccountDao,
            accountConverter: AccountConvertImp
    ): AccountRepo {
        return AccountRepoImp(apiAccountRepo, accountDao, accountConverter)
    }

    @Provides
    fun provideDomainRepo(
            apiDomainRepo: ApiDomainRepo,
            domainDao: DomainDao,
            domainConverter: DomainConvertImp,
            updateScheduleService: UpdateScheduleService
    ): DomainBaseRepo {
        return DomainRepoImp(apiDomainRepo, domainDao, domainConverter, updateScheduleService)
    }

    @Provides
    fun provideServerBaseRepo(
            apiServerRepo: ApiServerRepo,
            serverDao: ServerDao,
            serverConverter: ServerConvertImp,
            updateScheduleService: UpdateScheduleService
    ): ServerBaseRepo {
        return ServerRepoImp(apiServerRepo, serverDao, serverConverter, updateScheduleService)
    }

    @Provides
    fun provideServerActionsRepo(
            apiServerRepo: ApiServerRepo,
            actionDao: ActionDao,
            actionConverter: ActionConvertImp,
            updateScheduleService: UpdateScheduleService
    ) : ServerActionsRepo{
        return ServerRepoImp(apiServerRepo, actionDao, actionConverter, updateScheduleService)
    }

    @Provides
    fun provideServerStatisticsRepo(
            apiServerRepo: ApiServerRepo,
            statisticDao: StatisticDao,
            statisticConverter: StatisticConvertImp,
            updateScheduleService: UpdateScheduleService
    ) : ServerStatisticsRepo{
        return ServerRepoImp(apiServerRepo, statisticDao, statisticConverter, updateScheduleService)
    }

    @Provides
    fun provideServerBackupRepo(
            apiServerRepo: ApiServerRepo,
            backupDao: BackupDao,
            backupConverter: BackupConvertImp,
            updateScheduleService: UpdateScheduleService
    ) : ServerBackupRepo{
        return ServerRepoImp(apiServerRepo, backupDao, backupConverter, updateScheduleService)
    }

    @Provides
    fun provideServerLoadAveragesRepo(
            apiServerRepo: ApiServerRepo,
            loadAverageDao: LoadAverageDao,
            loadAverageConverter: LoadAverageConvertImp,
            updateScheduleService: UpdateScheduleService
    ) : ServerLoadAveragesRepo{
        return ServerRepoImp(apiServerRepo, loadAverageDao, loadAverageConverter, updateScheduleService)
    }

    @Provides
    fun provideUpdateScheduleRepo(
            updateScheduleDao : UpdateScheduleDao
    ): UpdateScheduleRepImp {
        return UpdateScheduleRepImp(updateScheduleDao)
    }
}