package com.campus.hub.data.remote

import android.content.Context
import com.campus.hub.data.local.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "http://10.0.2.2:5000/"

    fun create(context: Context): CampusApi {

        val tokenManager =
            TokenManager(context.applicationContext)

        val okHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(
                    AuthInterceptor(tokenManager)
                )
                .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(CampusApi::class.java)
    }
}