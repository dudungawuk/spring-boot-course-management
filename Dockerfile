# --- Stage 1: Build the application ---
# Use a specific Java version that includes Maven to build our app
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project file and download dependencies first for build caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of your application's source code
COPY src ./src

# Build the application and create the .jar file
RUN mvn package -DskipTests


# --- Stage 2: Create the final, small image ---
# Use a minimal Java runtime image
FROM eclipse-temurin:17-jre-focal

# Set the working directory
WORKDIR /app

# ----> THIS IS THE LINE YOU CORRECTLY IDENTIFIED AND UPDATED <----
COPY --from=build /app/target/coursemanagement-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# The command to run your application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]