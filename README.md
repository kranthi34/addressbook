# Address Book Project

This is a sample Java / Maven / Spring Boot (version 2+) application that can be used as a starter for creating a complete Address book project. Currently this project done by using csv file as data storage but we can also upgrade this with database. To do that in pom.xml we have to un comment two dependencies related to database and jpa than we have to add jpa logic to manage data

## How to Run 

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.11+ and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/addressbook-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2021-08-12 | 13:28:36.645 | INFO |  |  | o.s.boot.web.embedded.tomcat.TomcatWebServer | Tomcat started on port(s): 8080 (http) with context path ''
2021-08-12 | 13:28:36.646 | INFO |  |  | s.d.s.web.plugins.DocumentationPluginsBootstrapper | Context refreshed
2021-08-12 | 13:28:36.646 | INFO |  |  | s.d.s.web.plugins.DocumentationPluginsBootstrapper | Found 1 custom documentation plugin(s)
2021-08-12 | 13:28:36.649 | INFO |  |  | s.d.spring.web.scanners.ApiListingReferenceScanner | Scanning for api listing references
```

## About the Service

This had only one service to fetch persons addresses by filters
```
### To view Swagger 2

Run the server and browse to localhost:8080/swagger-ui.html
