package com.platdmit.simplecloudmanager.base.extensions

import android.view.View

/**
 * Base visibility handler for View components
 **/
fun View.visibleStat(status: Boolean){
    apply {
        visibility = if (status) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}