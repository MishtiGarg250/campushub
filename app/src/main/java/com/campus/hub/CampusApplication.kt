package com.campus.hub

import android.app.Application
import com.campus.hub.data.remote.RetrofitClient

class CampusHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        RetrofitClient.initialize(this)
    }
}