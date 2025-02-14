# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim 

# Set working directory inside the container
WORKDIR /app

# Install required dependencies
RUN apt-get update && apt-get install -y bash coreutils

# Copy all files from the project to the container
COPY . .

# Give execution permissions to the Maven wrapper
RUN chmod +x mvnw 

# Build the project using the Maven wrapper
RUN ./mvnw clean install 

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "target/your-app-name.jar"]
