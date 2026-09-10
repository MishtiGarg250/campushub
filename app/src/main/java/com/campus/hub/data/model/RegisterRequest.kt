package com.campus.hub.data.model

data class RegisterRequest (
    val name: String,
    val email: String,
    val password: String,
    val rollNo: String?,
    val branch: String?,
    val semester: Int?
)