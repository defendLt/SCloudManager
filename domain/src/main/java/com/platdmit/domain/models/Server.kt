package com.platdmit.domain.models
//TODO convert to data class
class Server {
    var id: Long
        private set
    var name: String
        private set
    private var memory = 0
    var vcpus = 0
        private set
    var disk = 0
        private set
    var region: String? = null
        private set
    lateinit var image: Image
    var backupPriceHourly = 0.0
        private set
    var paymentDate: String? = null
        private set
    var paymentPrice: String? = null
        private set
    var uptime: String
        private set
    var isLocked = false
        private set
    var status: String
        private set
    var createdAt: String? = null
        private set
    var startedFirstAt: String? = null
        private set
    var startedAt: String? = null
        private set
    var isInstall = false
        private set
    var isError = false
        private set
    var password: String? = null
        private set
    var v4Ip: String? = null
        private set
    var isMbit200 = false
        private set
    private lateinit var serverLog: List<Action>

    constructor(id: Long, name: String, uptime: String, status: String) {
        this.id = id
        this.name = name
        this.uptime = uptime
        this.status = status
    }

    constructor(id: Long, name: String, memory: Int, vcpus: Int, disk: Int, region: String?, backupPriceHourly: Double, paymentDate: String?, paymentPrice: String?, uptime: String, locked: Boolean, status: String, createdAt: String?, startedFirstAt: String?, startedAt: String?, isInstall: Boolean, isError: Boolean, password: String?, v4Ip: String?, mbit200: Boolean) {
        this.id = id
        this.name = name
        this.memory = memory
        this.vcpus = vcpus
        this.disk = disk
        this.region = region
        this.backupPriceHourly = backupPriceHourly
        this.paymentDate = paymentDate
        this.paymentPrice = paymentPrice
        this.uptime = uptime
        isLocked = locked
        this.status = status
        this.createdAt = createdAt
        this.startedFirstAt = startedFirstAt
        this.startedAt = startedAt
        this.isInstall = isInstall
        this.isError = isError
        this.password = password
        this.v4Ip = v4Ip
        isMbit200 = mbit200
    }

}