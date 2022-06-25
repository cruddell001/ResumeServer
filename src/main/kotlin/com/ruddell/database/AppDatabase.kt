package com.ruddell.database

import com.mysql.jdbc.Connection
import com.ruddell.json.jsonDeserializer
import com.ruddell.models.Resume
import kotlinx.serialization.decodeFromString
import java.io.File
import java.sql.DriverManager

object AppDatabase {

    private var _db: Connection? = null
    private var _dao: Dao? = null
    val dao: Dao get() {
        _dao?.let { return it }
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        (DriverManager.getConnection(DbConfig.dbConnectionPath) as Connection).also { conn ->
            _db = conn
            return AppDao(conn).also {
                _dao = it
                initializeDatabase()
            }
        }
    }

    private fun initializeDatabase() {
        Schema.sqlStatements.forEach {
            _db?.createStatement()?.execute(it)
        }
        if (dao.getWorkExperiences().isEmpty()) {
            importData()
        }
    }

    private fun importData() {
        val json = File("content/initialcontent.json").readText()
        val resume: Resume? = try { jsonDeserializer.decodeFromString(json) } catch (e: Exception) { e.printStackTrace(); null }
        resume?.let {
            it.workExperience.forEach {
                dao.insertWorkExperience(it)
            }
            it.education.forEach {
                dao.insertEducation(it)
            }
            it.skills.forEach {
                dao.insertSkill(it)
            }
        }
    }

}
