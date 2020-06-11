package com.platdmit.simplecloudmanager.domain;

import android.app.Application;

import com.platdmit.simplecloudmanager.data.api.implement.ApiAccountRepoImp;
import com.platdmit.simplecloudmanager.data.database.DbManager;
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyModule;
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService;

import net.danlew.android.joda.JodaTimeAndroid;

import androidx.room.Room;

public class SCMApp extends Application {
    private static ActualApiKeyService mActualApiKeyService;
    private static DbManager mDb;

    @Override
    public void onCreate() {
        super.onCreate();

        //Init time library
        JodaTimeAndroid.init(this);

        //Init Db
        mDb = Room.databaseBuilder(this, DbManager.class, "sc_manager").build();

        //Init apiKey updater
        mActualApiKeyService = new ActualApiKeyModule(
                new ApiAccountRepoImp()
        );
    }

    public static ActualApiKeyService getActualApiKeyService(){
        return mActualApiKeyService;
    }

    public static DbManager getDb() {
        return mDb;
    }

}
