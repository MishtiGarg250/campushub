package com.campus.hub.data.model

data class Course(
    val id: Int,
    val name: String,
    val code : String,
    val teacher : String,
    val room: String,
    val startTime : String,
    val endTime: String,
)