package com.platdmit.simplecloudmanager

import android.app.Application
import androidx.room.Room
import com.platdmit.data.api.implement.ApiAccountRepoImp
import com.platdmit.data.database.DbManager
import com.platdmit.domain.helpers.ActualApiKeyModule
import com.platdmit.data.helpers.ActualApiKeyService
import com.platdmit.domain.helpers.ActualApiKeyServiceManager
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

        lateinit var actualApiKeyService: ActualApiKeyServiceManager
            private set
        lateinit var db: DbManager
            private set

    }
}