package com.platdmit.simplecloudmanager.base.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.platdmit.simplecloudmanager.utilities.UiVisibilityStatus

/**
 * Base handler for alert messages
**/
fun Fragment.showResultMessage(message: String) {
    view?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
    }
}

fun Fragment.showResultMessage(messageId: Int) {
    view?.let {
        Snackbar.make(it, messageId, Snackbar.LENGTH_SHORT).show()
    }
}

/**
 * Base visibility handler for loader_hover
 **/
fun Fragment.setLoaderStatus(status: Boolean){
    (activity as? UiVisibilityStatus)?.setVisibilityLoader(status)
}