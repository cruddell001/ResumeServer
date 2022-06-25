package com.ruddell.database


object Schema {
    private val workExperienceTable: String = """
        CREATE TABLE IF NOT EXISTS work_experience (
            id INT NOT NULL AUTO_INCREMENT,
            position VARCHAR(128),
            company_name VARCHAR(128),
            dates VARCHAR(128),
            description TEXT,
            PRIMARY KEY (id)
        );
    """.trimIndent()

    private val educationTable: String = """
        CREATE TABLE IF NOT EXISTS education (
            id INT NOT NULL AUTO_INCREMENT,
            school_name VARCHAR(128),
            degree_name VARCHAR(128),
            graduation_date VARCHAR(128),
            gpa FLOAT,
            website VARCHAR(128),
            PRIMARY KEY (id)
        );
    """.trimIndent()

    private val skillTable: String = """
        CREATE TABLE IF NOT EXISTS skill (
            id INT NOT NULL AUTO_INCREMENT,
            category_name VARCHAR(128),
            item_names VARCHAR(128),
            PRIMARY KEY (id)
        );
    """.trimIndent()

    val sqlStatements = listOf(workExperienceTable, educationTable, skillTable)
}
