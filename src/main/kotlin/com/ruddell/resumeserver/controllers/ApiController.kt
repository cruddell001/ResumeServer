package com.ruddell.resumeserver.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ApiController {

    @RequestMapping("/")
    fun home() = "index"

    @RequestMapping("/resume/json", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getResumeContent() : ResponseEntity<String> {

        return ResponseEntity("{}", HttpStatus.OK)
    }
}