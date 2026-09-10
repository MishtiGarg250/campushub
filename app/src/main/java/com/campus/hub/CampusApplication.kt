package com.campus.hub

import android.app.Application
import com.campus.hub.data.remote.CampusApi
import com.campus.hub.data.remote.RetrofitClient

class CampusHubApplication : Application() {

    lateinit var api: CampusApi
        private set

    override fun onCreate() {
        super.onCreate()

        api = RetrofitClient.create(this)
    }
}