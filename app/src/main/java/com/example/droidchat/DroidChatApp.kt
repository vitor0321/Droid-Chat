package com.example.droidchat

import android.app.Application
import com.survicate.surveys.Survicate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class DroidChatApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Survicate.init(this)
    }
}