#Spring-Boot-Product-System
Spring Boot Crud Application with Thymeleaf, JPA, Spring Security.

Product System
This project based on the Spring Boot project and used packages:

Spring Boot
Spring Data
Spring Security
Thymeleaf
JavaScript
Maven
Installation
1. Clone the application
 $ git clone https://github.com/batuhaniskr/spring-boot-product-automation.git 
2. Database Configuration
MySQL is used as the database.

Open src/main/resources/application.properties
If you run the application locally, change spring.datasource.username and spring.datasource.password as per your mysql installation
The project is created with Maven.
3. Launch
To run the application through the docker:

mvn clean package
docker-compose up
or to run the application locally:

mvn clean install
mvn spring-boot:run
commands run the application.

Application runs from localhost:8080/products

There are users of type user and admin.

Admin Credential:

email: admin@gmail.com  password: admin
İf you try as user, you will get access denied on delete product page. You have to try as admin.

The application runs from http://localhost:8080/products
