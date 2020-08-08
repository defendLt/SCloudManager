package com.platdmit.simplecloudmanager.di

import com.platdmit.data.AccountRepoImp
import com.platdmit.data.DomainRepoImp
import com.platdmit.data.ServerRepoImp
import com.platdmit.data.UpdateScheduleRepImp
import com.platdmit.data.retrofit.ApiAccountRepoImp
import com.platdmit.data.retrofit.ApiDomainRepoImp
import com.platdmit.data.retrofit.ApiServerRepoImp
import com.platdmit.data.converters.*
import com.platdmit.data.room.dao.*
import com.platdmit.domain.helpers.UpdateScheduleService
import com.platdmit.domain.repositories.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideAccountRepo(
            apiAccountRepo: ApiAccountRepoImp,
            accountDao: AccountDao,
            accountConverter: AccountConvertImp
    ): AccountRepo {
        return AccountRepoImp(apiAccountRepo, accountDao, accountConverter)
    }

    @Provides
    fun provideDomainRepo(
            apiDomainRepo: ApiDomainRepoImp,
            domainDao: DomainDao,
            domainConverter: DomainConvertImp,
            updateScheduleService: UpdateScheduleService
    ): DomainBaseRepo {
        return DomainRepoImp(apiDomainRepo, domainDao, domainConverter, updateScheduleService)
    }

    @Provides
    fun provideServerBaseRepo(
            apiServerRepo: ApiServerRepoImp,
            serverDao: ServerDao,
            serverConverter: ServerConvertImp,
            updateScheduleService: UpdateScheduleService
    ): ServerBaseRepo {
        return ServerRepoImp(apiServerRepo, serverDao, serverConverter, updateScheduleService)
    }

    @Provides
    fun provideServerActionsRepo(
            apiServerRepo: ApiServerRepoImp,
            actionDao: ActionDao,
            actionConverter: ActionConvertImp,
            updateScheduleService: UpdateScheduleService
    ) : ServerActionsRepo {
        return ServerRepoImp(apiServerRepo, actionDao, actionConverter, updateScheduleService)
    }

    @Provides
    fun provideServerStatisticsRepo(
            apiServerRepo: ApiServerRepoImp,
            statisticDao: StatisticDao,
            statisticConverter: StatisticConvertImp,
            updateScheduleService: UpdateScheduleService
    ) : ServerStatisticsRepo {
        return ServerRepoImp(apiServerRepo, statisticDao, statisticConverter, updateScheduleService)
    }

    @Provides
    fun provideServerBackupRepo(
            apiServerRepo: ApiServerRepoImp,
            backupDao: BackupDao,
            backupConverter: BackupConvertImp,
            updateScheduleService: UpdateScheduleService
    ) : ServerBackupRepo {
        return ServerRepoImp(apiServerRepo, backupDao, backupConverter, updateScheduleService)
    }

    @Provides
    fun provideServerLoadAveragesRepo(
            apiServerRepo: ApiServerRepoImp,
            loadAverageDao: LoadAverageDao,
            loadAverageConverter: LoadAverageConvertImp,
            updateScheduleService: UpdateScheduleService
    ) : ServerLoadAveragesRepo {
        return ServerRepoImp(apiServerRepo, loadAverageDao, loadAverageConverter, updateScheduleService)
    }

    @Provides
    fun provideUpdateScheduleRepo(
            updateScheduleDao : UpdateScheduleDao,
            updateScheduleConvert: UpdateScheduleConvertImp
    ): UpdateScheduleRepImp {
        return UpdateScheduleRepImp(updateScheduleDao, updateScheduleConvert)
    }
}