package com.platdmit.domain.repo.implement

import com.platdmit.data.database.dao.UpdateScheduleDao
import com.platdmit.data.database.entity.DbUpdateSchedule
import com.platdmit.domain.repo.UpdateScheduleRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class UpdateScheduleRepImp(
        private val updateScheduleDao : UpdateScheduleDao
) : UpdateScheduleRepo<DbUpdateSchedule> {

    override fun getUpdateTimeList(): List<DbUpdateSchedule>? {
        return updateScheduleDao.getScheduleAll()
    }

    override fun getUpdateTime(key: String): Long {
        return updateScheduleDao.getScheduleForName(key)?.nextUpdate ?: 0
    }

    override fun setUpdateTime(key: String, nextUpdate: Long) {
        Completable.fromAction {updateScheduleDao.insert(DbUpdateSchedule(key, nextUpdate))}.observeOn(Schedulers.io()).subscribe()
    }
}