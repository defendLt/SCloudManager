package com.platdmit.simplecloudmanager.screens.server

interface ServerTabFragment<F> {
    fun getTitle(): CharSequence
    fun getInstance(): F
}