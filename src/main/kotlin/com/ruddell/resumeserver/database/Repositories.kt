package com.ruddell.resumeserver.database

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import javax.persistence.Id

interface WorkExperienceRepository : CrudRepository<WorkExperience, Int> {
    fun findAll(sort:Sort) : Set<WorkExperience>
}
interface EducationRepository : CrudRepository<Education, Int> {
    fun findAll(sort:Sort) : Set<Education>
}
interface SkillRepository : CrudRepository<Skill, Int> {
    fun findAll(sort:Sort) : Set<Skill>
}