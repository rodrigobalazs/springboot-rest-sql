## Spring Boot REST API
This repository contains an Spring Boot Store Application. The app provides a REST API related to Categories and
Products associated to the Categories.<br/>
Although the domain model is very simple, the goal is to provide examples of Spring related technologies, like
Spring Boot, Spring Data, etc.

### 🔧 Technology Stack

```
Java 17
Spring Boot 3 ( REST API )
Spring Data ( MySQL )
Testing (  Junit 5  /  Mockito  /  TestContainers  )
Misc Libraries (  Maven  /  Docker  /  SpringDoc OpenAPI  /  Apache Commons  /  Lombok  )
```

### ⚒️ Getting Started

```bash
# clone the project
git clone https://github.com/rodrigobalazs/springboot-rest-sql.git;
cd springboot-rest-sql;

# start a mysql docker container
docker run --name store_db -e MYSQL_DATABASE=store_db -e MYSQL_USER=<user> \
    -e MYSQL_PASSWORD=<pass> -e MYSQL_ROOT_PASSWORD=<pass> \
    -p 3306:3306 -d mysql:latest;

# make sure to update application.properties with the
# MYSQL_USER and MYSQL_PASSWORD values defined in the previous point
spring.datasource.username=<user>
spring.datasource.password=<pass>

# compile and start the spring boot server
mvn clean install;
mvn spring-boot:run;
```

### 💡 API Examples

#### 1. Get all the available Categories =>
```
curl -X GET http://localhost:8080/category/getCategories    -H 'accept: application/json';
```

#### 2. Retrieves a Category by Category Name =>
```
curl -X GET http://localhost:8080/category/name/Furniture   -H 'accept: application/json';
```


#### 3. Deletes a Category by Category id =>
```
curl -X DELETE http://localhost:8080/category/id/1          -H 'accept: application/json';
```

#### 4. Creates a new 'Sports Equipment' Category with an associated Product =>
```
curl -X POST http://localhost:8080/category  \
     -H 'accept: application/json'  \
     -H 'Content-Type: application/json' -d \
'{
    "name": "Sports Equipment",
    "products": [
        {
            "name": "Triathlon Suit",
            "price": 250.1
        }
    ]
}';
```

#### 5. Updates the product 'Sweater Tangle Essential' price from $50.4 to $90.8 =>
```
curl -X PUT http://localhost:8080/category/id/2  \
     -H 'accept: application/json' \
     -H 'Content-Type: application/json' -d \
'{
    "name": "Clothing",
    "products": [
        {
            "id": 4,
            "name": "Sweater Tangle Essential",
            "price": 90.8
        }
    ]
}';
```

### 🔍 API Documentation / Swagger

the API documentation could be accessed from => http://localhost:8080/swagger-ui/index.html

![](https://github.com/rodrigobalazs/springboot-rest-sql/blob/main/src/main/resources/static/api_swagger.png)
