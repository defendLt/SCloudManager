package com.platdmit.simplecloudmanager.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkHelperImpl @Inject constructor(
        @ApplicationContext private val context: Context
) : NetworkHelper {

    override fun getNetworkStatus(): Boolean {
        var result = true
        val connectivityManager: ConnectivityManager? = context.getSystemService()

        connectivityManager?.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val capabilities = getNetworkCapabilities(activeNetwork)
                if (capabilities == null) {
                    result = false
                }
            } else {
                val activeNetwork = activeNetworkInfo
                if (activeNetwork?.isConnectedOrConnecting == false) {
                    result = false
                }
            }
        }

        return result
    }
}