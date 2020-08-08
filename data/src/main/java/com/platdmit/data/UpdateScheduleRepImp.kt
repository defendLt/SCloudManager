package com.platdmit.data

import com.platdmit.data.room.dao.UpdateScheduleDao
import com.platdmit.data.room.entity.DbUpdateSchedule
import com.platdmit.domain.converters.UpdateScheduleConverter
import com.platdmit.domain.models.UpdateSchedule
import com.platdmit.domain.repositories.UpdateScheduleRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class UpdateScheduleRepImp(
        private val updateScheduleDao : UpdateScheduleDao,
        private val updateScheduleConverter: UpdateScheduleConverter<DbUpdateSchedule, UpdateSchedule>
) : UpdateScheduleRepo {

    override fun getUpdateTimeList(): List<UpdateSchedule>? {
        return updateScheduleDao.getScheduleAll()?.map { updateScheduleConverter.fromDbToDomain(it) }
    }

    override fun getUpdateTime(key: String): Long {
        return updateScheduleDao.getScheduleForName(key)?.nextUpdate ?: 0
    }

    override fun setUpdateTime(key: String, nextUpdate: Long) {
        Completable.fromAction {updateScheduleDao.insert(DbUpdateSchedule(key, nextUpdate))}.observeOn(Schedulers.io()).subscribe()
    }
}