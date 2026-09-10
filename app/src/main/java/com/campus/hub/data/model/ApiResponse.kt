package com.campus.hub.data.model

data class ApiResponse<T>(
    val success:Boolean,
    val data: T
)
