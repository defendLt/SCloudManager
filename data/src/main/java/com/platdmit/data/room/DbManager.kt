package com.platdmit.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.platdmit.data.room.dao.*
import com.platdmit.data.room.entity.*

@Database(entities = [
    DbAccount::class, DbDomain::class, DbDomainRecord::class, DbServer::class,
    DbAction::class, DbBackup::class, DbLoadAverage::class, DbStatistic::class, DbUpdateSchedule::class
], version = 1, exportSchema = false)
abstract class DbManager : RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun domainDao(): DomainDao
    abstract fun domainRecordDao(): DomainRecordDao
    abstract fun serverDao(): ServerDao
    abstract fun actionDao(): ActionDao
    abstract fun backupDao(): BackupDao
    abstract fun loadAverageDao(): LoadAverageDao
    abstract fun statisticDao(): StatisticDao
    abstract fun updateScheduleDao(): UpdateScheduleDao

    companion object{
        const val DATABASE_NAME = "sc_manager"
    }
}