# Code Challenge

Code Challenge in Springboot Java

## Requirements

Java 8
SpringBoot 2.5
MySQL
Apache Tomcat

## Installation

1.- Use the package manager maven to install dependencies for SpringBoot
  1.1 import project from Maven o pom file

2.- Create a new Data base schema with the name "sucursal"

3.- Edit file application.yml in the line 32 and 33 with the username and password of your local database

4.- Run Apache Tomcat and Run Project


## Documentation

After run project you can consult the documentation in http://localhost:8080/demo_war/swagger-ui/

## API End points

"http://localhost:8080/demo_war/api/branchoffice" -> Get All Branch offices

"http://localhost:8080/demo_war/api/branchoffice/create" -> Create a new Branch office the body request is
  {
    name:"string",
    address:"string",
    latitude:double,
    longitude:double
  }
  
  "http://localhost:8080/demo_war/api/branchoffice/get-by-point?latitude=0&longitude=0" Get the branch office more near at the point 
  
  
