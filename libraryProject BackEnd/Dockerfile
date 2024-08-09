# Use an official Java image as a base
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application jar file to the working directory
COPY target/libraryProject.jar app.jar

# Expose the port your application will run on (adjust accordingly)
EXPOSE 8040

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
