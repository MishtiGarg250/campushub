package com.campus.hub.data.remote

import com.campus.hub.data.model.ApiResponse
import com.campus.hub.data.model.Assignment
import com.campus.hub.data.model.AuthResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.POST
import com.campus.hub.data.model.Course
import com.campus.hub.data.model.LoginRequest
import com.campus.hub.data.model.RegisterRequest

interface CampusApi {

    // =========================
    // AUTH
    // =========================

    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse

    @POST("api/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): AuthResponse


    // =========================
    // COURSES
    // =========================

    @GET("api/courses")
    suspend fun getCourses():
            ApiResponse<List<Course>>

    @GET("api/courses/{id}")
    suspend fun getCourseById(
        @Path("id") id: Int
    ): ApiResponse<Course>


    // =========================
    // ASSIGNMENTS
    // =========================

    @GET("api/assignments")
    suspend fun getAssignments():
            ApiResponse<List<Assignment>>

    @GET("api/assignments/{id}")
    suspend fun getAssignmentById(
        @Path("id") id: Int
    ): ApiResponse<Assignment>

}