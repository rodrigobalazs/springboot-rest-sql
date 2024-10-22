## Spring Boot REST API

This repository contains an Spring Boot Store Application. The app provides a REST API related to Categories and
Products associated to the Categories.
Although the domain model is very simple, the goal is to provide examples of Spring related technologies, like
Spring Boot, Spring Data, etc.

### 🔧 Technology Stack

###### Java 17
###### Spring Boot 3
###### Spring Data
###### Testing => Junit 5 / Mockito / TestContainers
###### Misc Libraries/Tech => Maven / Docker / Mysql / SpringDoc OpenAPI / Apache Commons / Lombok

### ⚒️ Getting Started

```bash
# clone the project
git clone https://github.com/rodrigobalazs/springboot-rest-sql.git;
cd springboot-rest-sql;

# start a mysql docker container
docker run --name <container_name> -e MYSQL_DATABASE=store_db -e MYSQL_USER=<db_user> \
    -e MYSQL_PASSWORD=<db_password> -e MYSQL_ROOT_PASSWORD=<root_user_password> -p 3306:3306 -d mysql:latest;

# make sure to update src/main/resources/application.properties with the <db_user> and <db_password> defined in the previous point
spring.datasource.username=<db_user>
spring.datasource.password=<db_password>

# compile and start the spring boot server
mvn clean install;
mvn spring-boot:run;
```
