package com.ruddell.plugins

import com.ruddell.database.AppDatabase
import com.ruddell.json.jsonSerializer
import com.ruddell.models.Resume
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.encodeToString

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/resume/json") {
            val workExperiences = AppDatabase.dao.getWorkExperiences()
            val education = AppDatabase.dao.getEducations()
            val skills = AppDatabase.dao.getSkills()
            val resume = Resume(workExperiences, education, skills)
            println(jsonSerializer.encodeToString(workExperiences))
            call.respondText(jsonSerializer.encodeToString(resume), contentType = ContentType.Application.Json)
        }
    }
}
