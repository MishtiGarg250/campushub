package com.campus.hub.data.model

data class Assignment (
    val id: Int,
    val title: String,
    val courseId: String,
    val duedate: String,
    val completed: Boolean= false
)