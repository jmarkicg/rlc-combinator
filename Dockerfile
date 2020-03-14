FROM maven:3.6.3-jdk-8 AS MAVEN_TOOL_CHAIN
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:8-jdk-alpine
COPY --from=MAVEN_TOOL_CHAIN /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
