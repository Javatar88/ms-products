FROM openjdk:11
EXPOSE 8080
ADD target/ms-products.jar ms-products.jar
ENTRYPOINT ["java","-jar","/ms-products.jar"]