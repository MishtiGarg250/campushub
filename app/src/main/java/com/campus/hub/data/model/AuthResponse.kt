package com.campus.hub.data.model

data class AuthData(
    val token: String,
    val user: User
)
data class AuthResponse (
    val success: Boolean,
    val data : AuthData?
)