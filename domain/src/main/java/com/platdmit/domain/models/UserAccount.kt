package com.platdmit.domain.models

data class UserAccount(val id: Long, var subAccount: Boolean = false, val login: String, val pass: String,
                       var balance: Double, val vpsLimit: Int, var main: Boolean = true, var pin: String = ""){
    lateinit var email: String
    lateinit var apiKey: String
}