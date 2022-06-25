package com.ruddell.models

import kotlinx.serialization.Serializable

@Serializable
data class WorkExperience(
    var id: Int = 0,
    val position: String = "",
    val companyName: String? = null,
    val dates: String? = null,
    val description: String? = null
)

@Serializable
data class Education (
    var id: Int = 0,
    val schoolName:String? = null,
    val degreeName:String? = null,
    val graduationDate:String? = null,
    val gpa:Float? = null,
    val website:String? = null
)

@Serializable
data class Skill (
    var id: Int = 0,
    var categoryName:String = "",
    var itemNames:List<String> = emptyList()
)