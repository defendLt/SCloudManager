package com.platdmit.simplecloudmanager.app.fragments

interface ServerTabFragment<F> {
    fun getTitle(): CharSequence
    fun getInstance(): F
}