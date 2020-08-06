package com.platdmit.mod_servers.screens.server

interface ServerTabFragment<F> {
    fun getTitle(): CharSequence
    fun getInstance(): F
}