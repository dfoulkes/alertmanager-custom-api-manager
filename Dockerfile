FROM eclipse-temurin:24-jre-alpine

# Copy the JAR file into the container
COPY build/libs/CriticalIssueRestService.jar app.jar
# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080