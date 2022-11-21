package com.transistorapps.userstestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private var mySplashScreen: SplashScreen? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mySplashScreen = installSplashScreen()
        mySplashScreen?.setKeepOnScreenCondition { true }
        setContentView(R.layout.activity_main)
        findNavController(R.id.nav_host_fragment)
    }

    fun dataLoaded() {
        mySplashScreen?.setKeepOnScreenCondition { false }
    }
}