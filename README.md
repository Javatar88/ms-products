# Spring-Boot-Product-System
Spring Boot Crud Application and H2 is used as the in memory database.
# Swagger Link
http://localhost:8080/swagger-ui/index.html
# DB console URL 
http://localhost:8080/h2-console

Database userName:root

Database password:root

# application URL 
http://localhost:8080/api/v1/products

Installation
1.Clone the application
 $ git clone https://github.com/Javatar88/ms-products.git
 
2.after  cloning the code run the below commands:
mvn clean install

mvn spring-boot:run





mvn clean package
docker-compose up
or to run the application locally:

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
