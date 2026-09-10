package com.campus.hub.data.repository

import com.campus.hub.data.local.TokenManager
import com.campus.hub.data.model.LoginRequest
import com.campus.hub.data.model.RegisterRequest
import com.campus.hub.data.model.User
import com.campus.hub.data.remote.RetrofitClient

class AuthRepository(
    private val tokenManager: TokenManager
) {

    private val api =
        RetrofitClient.api

    suspend fun login(
        email: String,
        password: String
    ): Result<User> {

        return try {

            val response =
                api.login(
                    LoginRequest(
                        email = email,
                        password = password
                    )
                )

            if (
                response.success &&
                response.data != null
            ) {

                tokenManager.saveToken(
                    response.data.token
                )

                Result.success(
                    response.data.user
                )

            } else {

                Result.failure(
                    Exception(
                        "Login failed"
                    )
                )
            }

        } catch (e: Exception) {

            Result.failure(e)
        }
    }


    suspend fun register(
        request: RegisterRequest
    ): Result<User> {

        return try {

            val response =
                api.register(request)

            if (
                response.success &&
                response.data != null
            ) {

                Result.success(
                    response.data.user
                )

            } else {

                Result.failure(
                    Exception(
                        "Registration failed"
                    )
                )
            }

        } catch (e: Exception) {

            Result.failure(e)
        }
    }
}