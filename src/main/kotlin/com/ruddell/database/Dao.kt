package com.ruddell.database

import com.ruddell.models.Education
import com.ruddell.models.Skill
import com.ruddell.models.WorkExperience

interface Dao {

    fun insertWorkExperience(workExperience: WorkExperience)
    fun insertEducation(education: Education)
    fun insertSkill(skill: Skill)

    fun getWorkExperiences(): List<WorkExperience>
    fun getEducations(): List<Education>
    fun getSkills(): List<Skill>

}
