package com.angelatest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AngelaTestApplication:Application(){
    override fun onCreate() {
        super.onCreate()
    }
}