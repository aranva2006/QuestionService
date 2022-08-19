# QuestionService
Rest api to send and validate a security question
Spring Boot "Microservice" Example Project
This is a sample Java / Maven / Spring Boot (version 2.1.1.RELEASE) application that can be used as a starter for creating a microservice complete.

How to Run
This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the java -jar command.

Clone this repository
gie clone 
Make sure you are using JDK 1.8 and Maven 3.x
You can build the project and run the tests by running mvn clean package
Once successfully built, you can run the service by one of these two methods:
        java -jar target/QuestionService-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
Check the stdout or boot_example.log file to make sure no exceptions are thrown
Once the application runs you should see something like this

2022-08-19 12:27:45.725  INFO 26684 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-08-19 12:27:45.755  INFO 26684 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]

About the Service

The service is just a simple Security Question providing REST service.

Get information about system health, configurations, etc.
http://localhost:8080/question/

Response: HTTP 200
Content: application/json;charset=UTF-8
Get a Question to answer
GET /question/
Accept: application/json
Content-Type: application/json

{
"qid":"d772c11e-7fb7-47e2-a091-531921009e11",
"questionStr":"\"Please sum the numbers 1,9,9\""
}

http://localhost:8080/question/clientResponse?qid=bb09a9bc-60b3-40ee-8e4f-ba5d044bbb25&answer=Great. The original question was "Please sum the numbers 1,9,9" and the answer is 19

Resonse :

That’s great
