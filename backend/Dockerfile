FROM openjdk:19-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","application.jar"]

ENTRYPOINT ["top", "-b"]