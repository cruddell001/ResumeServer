package com.ruddell.resumeserver.importer

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.ruddell.resumeserver.database.*
import java.io.File

object DataImporter
{

    var workRepo:WorkExperienceRepository? = null
    var eduRepo:EducationRepository? = null
    var skillRepo:SkillRepository? = null

    var hasRun = false

    fun importContent(workRepo:WorkExperienceRepository, eduRepo:EducationRepository, skillRepo:SkillRepository) {
        val contentFile = File("content/initialcontent.json")

        val gson = GsonBuilder().create()


        val resume = gson.fromJson(contentFile.readText() , JsonObject::class.java)

        workRepo.deleteAll()
        eduRepo.deleteAll()
        skillRepo.deleteAll()

        val workExperiences = gson.fromJson(resume.getAsJsonArray("workExperience"), arrayOf<WorkExperience>()::class.java)
        val educationItems = gson.fromJson(resume.getAsJsonArray("education"), arrayOf<Education>()::class.java)

        val skills = gson.fromJson(resume.getAsJsonArray("skills"), arrayOf<JsonObject>()::class.java)


        workExperiences?.forEach {
            println("found work item:${it.position} @ ${it.companyName}")
            workRepo.save(it)
        }

        educationItems?.forEach {
            eduRepo.save(it)
        }

        skills?.forEach {
            val skill = Skill.fromJson(it)
            skillRepo.save(skill)
        }

        hasRun = true
    }

}