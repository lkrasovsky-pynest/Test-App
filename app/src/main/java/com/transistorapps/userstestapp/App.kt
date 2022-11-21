package com.transistorapps.userstestapp

import android.app.Application
import com.transistorapps.userstestapp.di.AppComponent
import com.transistorapps.userstestapp.di.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var component: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
    }
}