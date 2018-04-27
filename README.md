
# Simple ambulance service manager.

Building
--------
 
 To build this project you will need Maven. You can get it at:
 
     http://maven.apache.org
     
 Install:
 
     mvn clean instal
     
 Run in a jetty server:
 
     mvn -pl rest-server/ jetty:run
     mvn -pl web-app/ jetty:run

 Once started, the application should be available at:
 
     http://localhost:8088/
