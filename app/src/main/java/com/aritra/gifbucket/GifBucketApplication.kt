package com.aritra.gifbucket

import android.app.Application
import com.aritra.gifbucket.di.components.AppComponent
import com.aritra.gifbucket.di.components.DaggerAppComponent
import com.aritra.gifbucket.di.modules.DBModule
import com.aritra.gifbucket.di.modules.NetworkModule

class GifBucketApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {

        super.onCreate()
        // initiate logging here according to build type
        // initiate app component
        appInstance = this
        appComponent = DaggerAppComponent.factory().create(
            this,
            this,
            NetworkModule(),
            DBModule()
        )
    }

    companion object{
        lateinit var appInstance: GifBucketApplication
    }
}