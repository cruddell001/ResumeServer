package com.ruddell.database

object DbConfig {
    private val dbHost = System.getenv("db_host") ?: "localhost"
    private val dbPort = System.getenv("db_port") ?: "3306"
    private val dbName = System.getenv("db_name") ?: ""
    private val dbUser = System.getenv("db_user") ?: ""
    private val dbPassword = System.getenv("db_pass") ?: ""

    val dbConnectionPath: String get() = "jdbc:mysql://$dbHost:$dbPort/$dbName?user=$dbUser&password=$dbPassword&useSSL=false"
}
