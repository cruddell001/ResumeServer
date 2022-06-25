package com.ruddell.models

import kotlinx.serialization.Serializable

@Serializable
data class Resume(
    val workExperience: List<WorkExperience>,
    val education: List<Education>,
    val skills: List<Skill>
)
