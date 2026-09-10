package com.campus.hub.data.repository

import com.campus.hub.data.model.Course
import com.campus.hub.data.remote.RetrofitClient
import com.campus.hub.data.model.Assignment

class CampusRepository {

    private val api = RetrofitClient.api

    suspend fun getCourses(): Result<List<Course>> {

        return try {

            val response = api.getCourses()

            if (response.success) {
                Result.success(response.data)
            } else {
                Result.failure(
                    Exception("Failed to fetch courses")
                )
            }

        } catch (e: Exception) {

            Result.failure(e)
        }
    }


    suspend fun getAssignments():
            Result<List<Assignment>> {

        return try {

            val response = api.getAssignments()

            if (response.success) {
                Result.success(response.data)
            } else {
                Result.failure(
                    Exception("Failed to fetch assignments")
                )
            }

        } catch (e: Exception) {

            Result.failure(e)
        }
    }
}