package com.ruddell.resumeserver

import com.ruddell.resumeserver.importer.DataImporter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ResumeserverApplication

fun main(args: Array<String>) {
    runApplication<ResumeserverApplication>(*args)
}
