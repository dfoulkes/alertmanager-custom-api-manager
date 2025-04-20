# Stage 1: Build for amd64
FROM eclipse-temurin:24-jre-alpine AS builder_amd64
COPY build/libs/CriticalIssueRestService.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080

# Stage 2: Build for arm64
FROM eclipse-temurin:24-jre-alpine AS builder_arm64
COPY build/libs/CriticalIssueRestService.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080