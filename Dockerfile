# Build stage
FROM maven:3.9.16-eclipse-temurin-17-alpine AS build-stage
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
COPY --from=build-stage /target/summarizer-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]