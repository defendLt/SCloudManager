package com.platdmit.mod_login.domain.models

data class UserAccount(
        val id: Long,
        val login: String,
        val pass: String,
        var balance: Double,
        val vpsLimit: Int,
        var subAccount: Boolean = false,
        var pin: String = "",
        var main: Boolean = true
){
    lateinit var email: String
    lateinit var apiKey: String
}