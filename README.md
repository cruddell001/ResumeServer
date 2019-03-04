# Resume Server
Backend server to support Resume App

This is a Spring Boot application designed to serve the Resume content for the Resume App.  
Data is kept in a json flat file and loaded into a MySql database.  

The server expects the flat file to be located at content/initialcontent.json.  
A sample file is included in this repo for reference.

## Build Instructions:

From the project root, run `mvn clean install`

This will compile the project into a jar file located at target/resumeserver-0.0.1-SNAPSHOT.jar.  This file should be uploaded to the server and run using `java -jar resumeserver-0.0.1-SNAPSHOT.jar`.

## About the project:
This project was built entirely using Kotlin.  Hibernate database support was added to provide a ORM-style database implementation with a MySql backing.  This also allows shared entity class files between the mobile app and this server.
