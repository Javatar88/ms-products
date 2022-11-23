# Spring-Boot-Product-System
Spring Boot Crud Application with H2 in memory database.

# Requirement
Java 11

Maven

# Java Docs

 java docs location is inside ms-products\JavaDocs

# Swagger Link
http://localhost:8080/swagger-ui/index.html
# DB console URL 
http://localhost:8080/h2-console

Database userName:root

Database password:root

# application URL 
http://localhost:8080/api/v1/products

# Normal Installation
1.Clone the application
 $ git clone https://github.com/Javatar88/ms-products.git
 
2.after  cloning the code run the below commands:

mvn clean install

mvn spring-boot:run

# Docker Installation
docker build -t ms-product.jar .

docker run -p 8080:8080 ms-product.jar





# Json Request Example
```yaml
{
    "name": "Lenovo Ideapad 3",
    "description": "Lenovo Ideapad 3 - intel core I5 - 8GB memory - 128gb ssd",
    "price": 200
  }
  {
    "name": "HP 17-cn0078cl",
    "description": "HP 17-cn0078cl - intel core I7 - 32GB memory - 128gb ssd",
    "price": 175
  }
