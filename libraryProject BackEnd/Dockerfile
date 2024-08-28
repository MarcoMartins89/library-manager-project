# Use an official Java image as a base
FROM openjdk:21-slim

ENV MY_APP_NAME libraryProject
ENV DATABASE_URL jdbc:mysql://db:3306/my_database

# Set the working directory in the container
WORKDIR /app

# Copy the application jar file to the working directory
COPY target/libraryProject-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application will run on (adjust accordingly)
EXPOSE 8040

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]






