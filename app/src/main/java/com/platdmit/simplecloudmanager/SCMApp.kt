package com.platdmit.simplecloudmanager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import net.danlew.android.joda.JodaTimeAndroid

@HiltAndroidApp
class SCMApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //Init time library
        JodaTimeAndroid.init(this)
    }
}