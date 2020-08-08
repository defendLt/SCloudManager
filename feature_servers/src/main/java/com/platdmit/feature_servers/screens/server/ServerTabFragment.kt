package com.platdmit.feature_servers.screens.server

interface ServerTabFragment<F> {
    fun getTitle(): CharSequence
    fun getInstance(): F
}