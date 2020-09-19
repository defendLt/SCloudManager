package com.platdmit.simplecloudmanager.base.extensions

import android.view.View

fun View.visibleStat(status: Boolean){
    apply {
        visibility = if (status) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}