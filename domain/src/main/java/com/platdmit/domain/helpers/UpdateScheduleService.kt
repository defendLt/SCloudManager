package com.platdmit.domain.helpers

interface UpdateScheduleService {
    fun setUpdateTime(key: String, timer: Int)
    fun setDefaultUpdateTime(key: String)
    fun getActualStatus(key: String): Boolean
}