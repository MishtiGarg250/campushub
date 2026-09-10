package com.campus.hub.data.remote

import android.content.Context
import com.campus.hub.data.local.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "https://androidbackend-1-vljt.onrender.com/"

    lateinit var api: CampusApi
        private set

    fun initialize(context: Context) {

        val tokenManager =
            TokenManager(context.applicationContext)

        val client =
            OkHttpClient.Builder()
                .addInterceptor(
                    AuthInterceptor(tokenManager)
                )
                .build()

        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(CampusApi::class.java)
    }
}