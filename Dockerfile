# Stage 1: Build for amd64
FROM amazoncorretto:23-alpine-jdk
COPY build/libs/CriticalIssueRestService.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080