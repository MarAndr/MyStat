package com.example.mystat

import android.app.Application
import com.example.mystat.db.Database
import timber.log.Timber

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        Database.init(this)
    }
}