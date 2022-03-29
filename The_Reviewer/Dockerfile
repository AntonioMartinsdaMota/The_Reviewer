
#########################################
# Packaged spring boot app using maven
#########################################
#FROM openjdk:16-jdk-alpine as as builder
FROM maven:3.8-openjdk-17-slim as builder

RUN mkdir -p /app
WORKDIR /app
ADD pom.xml .
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package -DskipTests

ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:17-jdk-alpine as runner
ENV MARIADB_DB=lms
ENV MARIADB_USER=root
ENV MARIADB_PASSWORD=mypass
ENV MARIADB_URL=jdbc:mariadb://mymariadb:3306/${MARIADB_DB}
ENV SERVER_PORT=8080
ENV HIBERNATE_DDL_AUTO=update
COPY --from=builder /app/target/*.jar /app.jar
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java","-jar","/app.jar"]