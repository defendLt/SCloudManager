package com.platdmit.base_utils.update_service.domain.repositories

import com.platdmit.base_utils.update_service.domain.models.UpdateSchedule

interface UpdateScheduleRepo {
    fun getUpdateTimeList(): List<com.platdmit.base_utils.update_service.domain.models.UpdateSchedule>?
    fun getUpdateTime(key: String): Long
    fun setUpdateTime(key: String, nextUpdate: Long)
}