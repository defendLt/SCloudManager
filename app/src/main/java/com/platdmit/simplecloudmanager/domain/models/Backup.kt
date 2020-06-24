package com.platdmit.simplecloudmanager.domain.models

data class Backup(val id: Long, val server: Long, val status: String, val name: String, val distribution: String, val slug: String, val isPublic: Boolean, val createdAt: String, val minDiskSize: String, val comment: String, val time: String, val priceHourly: String, val action: Long, val system: String)