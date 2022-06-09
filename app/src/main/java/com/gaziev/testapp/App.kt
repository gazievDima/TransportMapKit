package com.gaziev.testapp

import android.app.Application
import com.gaziev.testapp.di.AppComponent
import com.gaziev.testapp.di.AppModule
import com.gaziev.testapp.di.DaggerAppComponent
import com.yandex.mapkit.MapKitFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.API_KEY);

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        INSTANCE = this@App
    }

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var INSTANCE: Application
    }
}