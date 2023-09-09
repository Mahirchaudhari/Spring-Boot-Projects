## Currency Conversion Exchange Service 

Currency Conversion Exchnage Service Project Created in Spring Boot 

**Tech Stack** 

JAVA, SpringBoot, Maven

Spring Boot : Actuator,  Netflix Eureka, Spring Cloud, JPA, H2 Database, Spring Cloud Config, Open Feign, Resilience4j, Zipkin

**Small description of each dependency :**

**Actuator:** Actuator is mainly used to expose operational information about the running application — health, metrics, info, dump, env, etc. It uses HTTP endpoints or JMX beans to enable us to interact with it.

**Netfilx Eureka:** Eureka Server holds the information about all client-service applications registered on eureka. Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address.

**Open Feign:** OpenFeign makes writing web service clients easier, used to call other microservice and load balancing.

**Resilience4j:** Resilience4j helps with implementing resilient systems by managing fault tolerance for remote communications. It provide different higher-order method reference with a Circuit Breaker, Rate Limiter, Retry or Bulkhead 

**Zipkin:** Zipkin is a distributed tracing system. It helps gather timing data needed to troubleshoot latency problems in service architectures.

**How to convert microservice to docker image:**

1) Insatll Docker desktop in your machine. 
2) Add Image configuration in pom.xml file.
3) Try to build with command (mvn clean spring-boot:build-image).
4) Run command to start microservice (docker run -p 8000:8000 sbdocker/currency-ms-currency-exchange-service:1.0.0-SNAPSHOT)

**Supported Functionality:**

1) Convert Currency From USD to INR
2) Calculate Currency from USD to INR
3) Manage MicroServices with Netflix Edureka
4) Store data in H2 database

**How to Build Project?**
1) Install maven/ java 17 in your machine.
2) Go to each and every project and build with maven
3) JAR will be generated in each base module.
4) Excecute JAR and hit different URL based on port mentioned in application.properties

**Useful Basic Docker Command:**

1) docker container ls -a : List all the container
2) docker container prune : Remove all the container
3) docker run -p 5000:5000 -d dockerName/app-name:1.0.0-SNAPSHOP : Start the container
4) docker run -p 5000:5000 -d --restart=no dockerName/app-name:1.0.0-SNAPSHOP : It will not start with docker (No Auto start)
5) docker run -p 5000:5000 -d --restart=always dockerName/app-name:1.0.0-SNAPSHOP : It will start with docker (Auto start)
6) docker container stop container-id
7) docker events : Log all the event happening with containers
8) docker top container-id : Show the processes running
9) docker stats : show the states (CPU, Memory) of the container
10) docker container logs -f container-id
11) docker system df : docker demains details
