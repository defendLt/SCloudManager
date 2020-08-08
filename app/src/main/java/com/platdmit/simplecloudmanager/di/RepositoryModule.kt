package com.platdmit.simplecloudmanager.di

import com.platdmit.feature_login.data.AccountRepoImp
import com.platdmit.feature_domains.data.DomainRepoImp
import com.platdmit.feature_servers.data.ServerRepoImp
import com.platdmit.base_utils.update_service.data.UpdateScheduleRepImp
import com.platdmit.feature_login.data.retrofit.ApiAccountRepoImp
import com.platdmit.feature_domains.data.retrofit.ApiDomainRepoImp
import com.platdmit.feature_servers.data.retrofit.ApiServerRepoImp
import com.platdmit.base_utils.update_service.domain.UpdateScheduleService
import com.platdmit.feature_domains.data.converters.DomainConvertImp
import com.platdmit.feature_domains.data.room.dao.DomainDao
import com.platdmit.feature_domains.domain.repositories.DomainBaseRepo
import com.platdmit.feature_login.data.converters.AccountConvertImp
import com.platdmit.feature_login.data.room.dao.AccountDao
import com.platdmit.feature_login.domain.repositories.AccountRepo
import com.platdmit.mod_servers.data.converters.*
import com.platdmit.mod_servers.data.room.dao.*
import com.platdmit.mod_servers.domain.repositories.*
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
            updateScheduleService: com.platdmit.base_utils.update_service.domain.UpdateScheduleService
    ): DomainBaseRepo {
        return DomainRepoImp(apiDomainRepo, domainDao, domainConverter, updateScheduleService)
    }

    @Provides
    fun provideServerBaseRepo(
            apiServerRepo: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.ApiServerRepoImp,
            serverDao: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.ServerDao,
            serverConverter: _root_ide_package_.com.platdmit.feature_servers.data.converters.ServerConvertImp,
            updateScheduleService: com.platdmit.base_utils.update_service.domain.UpdateScheduleService
    ): _root_ide_package_.com.platdmit.feature_servers.domain.repositories.ServerBaseRepo {
        return _root_ide_package_.com.platdmit.feature_servers.data.ServerRepoImp(apiServerRepo, serverDao, serverConverter, updateScheduleService)
    }

    @Provides
    fun provideServerActionsRepo(
            apiServerRepo: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.ApiServerRepoImp,
            actionDao: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.ActionDao,
            actionConverter: _root_ide_package_.com.platdmit.feature_servers.data.converters.ActionConvertImp,
            updateScheduleService: com.platdmit.base_utils.update_service.domain.UpdateScheduleService
    ) : _root_ide_package_.com.platdmit.feature_servers.domain.repositories.ServerActionsRepo {
        return _root_ide_package_.com.platdmit.feature_servers.data.ServerRepoImp(apiServerRepo, actionDao, actionConverter, updateScheduleService)
    }

    @Provides
    fun provideServerStatisticsRepo(
            apiServerRepo: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.ApiServerRepoImp,
            statisticDao: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.StatisticDao,
            statisticConverter: _root_ide_package_.com.platdmit.feature_servers.data.converters.StatisticConvertImp,
            updateScheduleService: com.platdmit.base_utils.update_service.domain.UpdateScheduleService
    ) : _root_ide_package_.com.platdmit.feature_servers.domain.repositories.ServerStatisticsRepo {
        return _root_ide_package_.com.platdmit.feature_servers.data.ServerRepoImp(apiServerRepo, statisticDao, statisticConverter, updateScheduleService)
    }

    @Provides
    fun provideServerBackupRepo(
            apiServerRepo: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.ApiServerRepoImp,
            backupDao: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.BackupDao,
            backupConverter: _root_ide_package_.com.platdmit.feature_servers.data.converters.BackupConvertImp,
            updateScheduleService: com.platdmit.base_utils.update_service.domain.UpdateScheduleService
    ) : _root_ide_package_.com.platdmit.feature_servers.domain.repositories.ServerBackupRepo {
        return _root_ide_package_.com.platdmit.feature_servers.data.ServerRepoImp(apiServerRepo, backupDao, backupConverter, updateScheduleService)
    }

    @Provides
    fun provideServerLoadAveragesRepo(
            apiServerRepo: _root_ide_package_.com.platdmit.feature_servers.data.retrofit.ApiServerRepoImp,
            loadAverageDao: _root_ide_package_.com.platdmit.feature_servers.data.room.dao.LoadAverageDao,
            loadAverageConverter: _root_ide_package_.com.platdmit.feature_servers.data.converters.LoadAverageConvertImp,
            updateScheduleService: com.platdmit.base_utils.update_service.domain.UpdateScheduleService
    ) : _root_ide_package_.com.platdmit.feature_servers.domain.repositories.ServerLoadAveragesRepo {
        return _root_ide_package_.com.platdmit.feature_servers.data.ServerRepoImp(apiServerRepo, loadAverageDao, loadAverageConverter, updateScheduleService)
    }

    @Provides
    fun provideUpdateScheduleRepo(
            updateScheduleDao : com.platdmit.base_utils.update_service.data.room.dao.UpdateScheduleDao,
            updateScheduleConvert: com.platdmit.base_utils.update_service.data.converters.UpdateScheduleConvertImp
    ): com.platdmit.base_utils.update_service.data.UpdateScheduleRepImp {
        return com.platdmit.base_utils.update_service.data.UpdateScheduleRepImp(updateScheduleDao, updateScheduleConvert)
    }
}