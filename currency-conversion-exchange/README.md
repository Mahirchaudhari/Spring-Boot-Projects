## Currency Conversion Exchange Service 

Currency Conversion Exchnage Service Project Created in Spring Boot 

**Tech Stack** 

JAVA, SpringBoot, Maven

Spring Boot : Actuator,  Netflix Eureka, Spring Cloud, JPA, H2 Database, Spring Cloud Config, Open Feign, Resilience4j

**Small description of each dependency :**

**Actuator:** Actuator is mainly used to expose operational information about the running application — health, metrics, info, dump, env, etc. It uses HTTP endpoints or JMX beans to enable us to interact with it.

**Netfilx Eureka:** Eureka Server holds the information about all client-service applications registered on eureka. Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address.

**Open Feign:** OpenFeign makes writing web service clients easier, used to call other microservice and load balancing.

**Resilience4j:** Resilience4j helps with implementing resilient systems by managing fault tolerance for remote communications. It provide different higher-order method reference with a Circuit Breaker, Rate Limiter, Retry or Bulkhead 

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



