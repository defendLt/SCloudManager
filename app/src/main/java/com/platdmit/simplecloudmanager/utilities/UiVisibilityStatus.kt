package com.platdmit.simplecloudmanager.utilities

interface UiVisibilityStatus {
    fun setVisibilityToolbar(status: Boolean)
    fun setVisibilityNavigation(status: Boolean)
    fun setVisibilityLoader(status: Boolean)
}