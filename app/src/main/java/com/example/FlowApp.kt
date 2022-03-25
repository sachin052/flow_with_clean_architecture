package com.example

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FlowApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Log.e("the","ashdkjffad")
        Log.e("the","ashdkjffad")
    }
}