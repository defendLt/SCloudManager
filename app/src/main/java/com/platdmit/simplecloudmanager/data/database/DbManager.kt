package com.platdmit.simplecloudmanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.platdmit.simplecloudmanager.data.database.dao.*
import com.platdmit.simplecloudmanager.data.database.entity.*

@Database(entities = [
    DbAccount::class, DbDomain::class, DbDomainRecord::class, DbServer::class,
    DbAction::class, DbBackup::class, DbLoadAverage::class, DbStatistic::class, DbUpdateSchedule::class
], version = 1, exportSchema = false)
abstract class DbManager : RoomDatabase() {
    abstract fun mAccountDao(): AccountDao
    abstract fun mDomainDao(): DomainDao
    abstract fun mDomainRecordDao(): DomainRecordDao
    abstract fun mServerDao(): ServerDao
    abstract fun mActionDao(): ActionDao
    abstract fun mBackupDao(): BackupDao
    abstract fun mLoadAverageDao(): LoadAverageDao
    abstract fun mStatisticDao(): StatisticDao
    abstract fun mUpdateScheduleDao(): UpdateScheduleDao
}