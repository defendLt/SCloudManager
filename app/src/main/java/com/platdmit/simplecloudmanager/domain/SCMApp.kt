package com.platdmit.simplecloudmanager.domain

import android.app.Application
import androidx.room.Room
import com.platdmit.simplecloudmanager.data.api.implement.ApiAccountRepoImp
import com.platdmit.simplecloudmanager.data.database.DbManager
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyModule
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService
import net.danlew.android.joda.JodaTimeAndroid

class SCMApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //Init time library
        JodaTimeAndroid.init(this)

        //Init Db
        db = Room.databaseBuilder(this, DbManager::class.java, "sc_manager").build()

        //Init apiKey updater
        actualApiKeyService = ActualApiKeyModule(
                ApiAccountRepoImp()
        )
    }

    companion object {

        lateinit var actualApiKeyService: ActualApiKeyService
            private set
        lateinit var db: DbManager
            private set

    }
}