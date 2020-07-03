package com.platdmit.domain.helpers

import com.platdmit.domain.repo.UpdateScheduleRepo
import org.joda.time.DateTime
import java.util.*

class ContentUpdateService(
        private val updateScheduleRepo: UpdateScheduleRepo,
        private val lifeTime: Int = 900000
) : UpdateScheduleService  {
    private val activeTypes: MutableMap<String, DateTime> = HashMap()

    override fun setUpdateTime(key: String, timer: Int) {
        val updateTime = DateTime().plusMillis(timer)
        activeTypes[key] = updateTime
        updateScheduleRepo.setUpdateTime(key, updateTime.millis)
    }

    override fun setDefaultUpdateTime(key: String) {
        setUpdateTime(key, lifeTime)
    }

    override fun getActualStatus(key: String): Boolean {
        val updateTime = getUpdateTime(key)
        return DateTime().millis < updateTime
    }

    private fun getUpdateTime(key: String): Long {
        val updateTime = activeTypes[key]
        return if (updateTime != null) {
            updateTime.millis
        } else {
            val dbUpdateTime = updateScheduleRepo.getUpdateTime(key)
            if (dbUpdateTime > 0) {
                activeTypes[key] = DateTime(dbUpdateTime)
            }
            dbUpdateTime
        }
    }

    companion object {
        private val TAG = ContentUpdateService::class.java.simpleName
    }
}