# Spring JPA Blueprint

Spring Boot 2.7.x based backend to demonstrate some JPA stuff

**Used Tech Stack**
- Java 17 LTS
- Spring Boot 2.7.x
- Spring Data JPA
- Mapstruct
- Lombok
- Validation
- MariaDB Driver
- Hikari connection pool
- SpringDoc
- Liquibase

### Prerequisites
- A running MariaDB on localhost, port 3306 with a "jpablueprint" schema. Or change the app properties for your needs.

**To build and run the demo on Port 8080: mvn clean spring-boot:run**

## I know that ....
#### 1. There are a lot of options how to handle entities and dto´s! This demo does a DTO first approach, because some / most(?) people are having trouble with the managed context of a JPA entity. Please do not discuss this with me.
#### 2. Also DON´T discuss the entity model, it´s just to demo stuff!

