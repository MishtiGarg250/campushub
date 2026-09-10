package com.campus.hub.data.model

data class User(
   val id: Int,
   val name: String,
   val email: String,
   val rollNo: String?,
   val branch: String?,
   val semester: Int?
)