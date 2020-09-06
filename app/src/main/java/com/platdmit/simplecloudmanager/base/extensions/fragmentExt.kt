package com.platdmit.simplecloudmanager.base.extensions

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.platdmit.simplecloudmanager.utilities.UiVisibilityStatus

/**
 * Base handler for alert messages
**/
fun Fragment.showResultMessage(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.showResultMessage(messageId: Int) {
    Snackbar.make(requireView(), messageId, Snackbar.LENGTH_SHORT).show()
}

/**
 * Base visibility handler for loader_hover
 **/
fun Fragment.setLoaderStatus(status: Boolean){
    (activity as? UiVisibilityStatus)?.setVisibilityLoader(status)
}