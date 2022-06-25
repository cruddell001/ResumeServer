package com.ruddell.database

import com.mysql.jdbc.Connection
import com.ruddell.models.Education
import com.ruddell.models.Skill
import com.ruddell.models.WorkExperience
import java.sql.ResultSet

class AppDao(val conn: Connection) : Dao {

    override fun insertWorkExperience(workExperience: WorkExperience) {
        val sql = """
            INSERT INTO work_experience (position, company_name, dates, description) VALUES (?,?,?,?);
        """.trimIndent()
        conn.prepareStatement(sql).apply {
            conn.autoCommit = false
            // set the variables
            setString(1, workExperience.position)
            setString(2, workExperience.companyName)
            setString(3, workExperience.dates)
            setString(4, workExperience.description)
            execute()
            conn.commit()
        }
    }

    override fun insertEducation(education: Education) {
        val sql = """
            INSERT INTO education (school_name, degree_name, graduation_date, gpa, website) VALUES (?,?,?,?,?);
        """.trimIndent()
        conn.prepareStatement(sql).apply {
            conn.autoCommit = false
            // set the variables
            setString(1, education.schoolName)
            setString(2, education.degreeName)
            setString(3, education.graduationDate)
            setFloat(4, education.gpa ?: 0f)
            setString(5, education.website)
            execute()
            conn.commit()
        }
    }

    override fun insertSkill(skill: Skill) {
        val sql = """
            INSERT INTO skill (category_name, item_names) VALUES (?,?);
        """.trimIndent()
        conn.prepareStatement(sql).apply {
            conn.autoCommit = false
            // set the variables
            setString(1, skill.categoryName)
            setString(2, skill.itemNames.joinToString(","))
            execute()
            conn.commit()
        }
    }

    override fun getWorkExperiences(): List<WorkExperience> = getResult("work_experience") { it.workExperience() }
    override fun getEducations(): List<Education> = getResult("education") { it.education() }
    override fun getSkills(): List<Skill> = getResult("skill") { it.skill() }

    private fun ResultSet.workExperience(): WorkExperience = WorkExperience(getInt(1), getString(2), getString(3), getString(4), getString(5)).also { println("getting work experience for ${getString(3)}") }
    private fun ResultSet.education(): Education = Education(getInt(1), getString(2), getString(3), getString(4), getFloat(5), getString(6))
    private fun ResultSet.skill(): Skill = Skill(getInt(1), getString(2), getString(3).split(","))

    private inline fun <reified T>getResult(table: String, block: (ResultSet) -> T): List<T> {
        val sql = "SELECT * FROM $table;"
        val resp = conn.createStatement().executeQuery(sql)
        val list = ArrayList<T>()
        while (resp.next()) {
            list.add(block(resp))
        }
        return list
    }
}