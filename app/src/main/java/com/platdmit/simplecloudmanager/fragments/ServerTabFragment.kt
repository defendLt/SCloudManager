package com.platdmit.simplecloudmanager.fragments

interface ServerTabFragment<F> {
    fun getTitle(): CharSequence
    fun getInstance(): F
}