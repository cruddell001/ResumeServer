# Resume Server
Backend server to support Resume App, powered by https://my-resume.app/data

This is a Ktor application designed to serve the resume content for the My Resume App.  
Data is kept in a json flat file and loaded into a MySql database.  

The server expects the flat file to be located at content/initialcontent.json.  
A sample file is included in this repo for reference.

## Build Instructions:

From the project root, run `./gradlew shadowJar`.

This will compile the project into a jar file located at `build/libs/ResumeServerKtor-0.0.1-all.jar`.  This file should be uploaded to the server and run using `java -jar resumeserver-0.0.1-SNAPSHOT.jar`.

## About the project:
This project was built entirely using Kotlin.  The MySql JDBC library was added to provide database support.  A custom mapping between database and model files was created.  This also allows shared entity class files between the mobile app and this server.
