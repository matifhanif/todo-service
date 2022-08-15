FROM maven:3-openjdk-18 AS builder
WORKDIR /home/app
COPY src src

COPY pom.xml pom.xml

RUN mvn clean package

FROM openjdk:18-jdk-slim

WORKDIR /home/app

COPY --from=builder /home/app/target/todo-service.jar /home/app/todo-service.jar

# change to the port you want to use with your app
EXPOSE 8080

ENTRYPOINT ["java", "-jar","/home/app/todo-service.jar"]

