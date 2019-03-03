package com.ruddell.resumeserver.controllers

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.ruddell.resumeserver.database.EducationRepository
import com.ruddell.resumeserver.database.SkillRepository
import com.ruddell.resumeserver.database.WorkExperienceRepository
import com.ruddell.resumeserver.importer.DataImporter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import java.io.File

@Controller
class ApiController
(
        val workRepo: WorkExperienceRepository,
        val eduRepo: EducationRepository,
        val skillRepo: SkillRepository
)
{

    @RequestMapping("/")
    fun home() = "index"

    @RequestMapping("/resume/json", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getResumeContent() : ResponseEntity<String> {

        if (!DataImporter.hasRun) DataImporter.importContent(workRepo, eduRepo, skillRepo)

        val gson = Gson()

        val resume = JsonObject()
        val workExperiences = JsonArray()
        workRepo.findAll(sort).forEach {
            workExperiences.add(gson.toJsonTree(it))
        }
        resume.add("workExperience", workExperiences)

        val educations = JsonArray()
        eduRepo.findAll(sort).forEach {
            educations.add(gson.toJsonTree(it))
        }
        resume.add("education", educations)

        val skills = JsonArray()
        skillRepo.findAll(sort).forEach {
            skills.add(it.toJson())
        }
        resume.add("skills", skills)

        return ResponseEntity(resume.toString(), HttpStatus.OK)
    }

    val sort = Sort(Sort.Direction.ASC, "id")
}