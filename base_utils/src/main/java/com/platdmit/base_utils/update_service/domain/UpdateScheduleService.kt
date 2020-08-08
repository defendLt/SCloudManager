package com.platdmit.base_utils.update_service.domain

interface UpdateScheduleService {
    fun setUpdateTime(key: String, timer: Int)
    fun setDefaultUpdateTime(key: String)
    fun getActualStatus(key: String): Boolean
}