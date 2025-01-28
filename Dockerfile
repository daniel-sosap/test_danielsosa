FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/restdsl-0.0.1-SNAPSHOT.jar restdsl.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "restdsl.jar"]