package com.platdmit.simplecloudmanager.di

import android.content.Context
import androidx.room.Room
import com.platdmit.base_db.data.room.DbManager
import com.platdmit.base_utils.update_service.data.room.dao.UpdateScheduleDao
import com.platdmit.feature_domains.data.room.dao.DomainDao
import com.platdmit.feature_domains.data.room.dao.DomainRecordDao
import com.platdmit.feature_login.data.room.dao.AccountDao
import com.platdmit.feature_servers.data.room.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideSCDb(@ApplicationContext context: Context): DbManager {
        return Room
                .databaseBuilder(
                        context,
                        DbManager::class.java,
                        DbManager.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideAccountDao(dbManager: DbManager): AccountDao {
        return dbManager.accountDao()
    }

    @Singleton
    @Provides
    fun provideDomainDao(dbManager: DbManager): DomainDao {
        return dbManager.domainDao()
    }

    @Singleton
    @Provides
    fun provideDomainRecordDao(dbManager: DbManager): DomainRecordDao {
        return dbManager.domainRecordDao()
    }

    @Singleton
    @Provides
    fun provideServerDao(dbManager: DbManager): ServerDao {
        return dbManager.serverDao()
    }

    @Singleton
    @Provides
    fun provideActionDao(dbManager: DbManager): ActionDao {
        return dbManager.actionDao()
    }

    @Singleton
    @Provides
    fun provideBackupDao(dbManager: DbManager): BackupDao {
        return dbManager.backupDao()
    }

    @Singleton
    @Provides
    fun provideLoadAverageDao(dbManager: DbManager): LoadAverageDao {
        return dbManager.loadAverageDao()
    }

    @Singleton
    @Provides
    fun provideStatisticDao(dbManager: DbManager): StatisticDao {
        return dbManager.statisticDao()
    }

    @Singleton
    @Provides
    fun provideUpdateScheduleDao(dbManager: DbManager): UpdateScheduleDao {
        return dbManager.updateScheduleDao()
    }
}