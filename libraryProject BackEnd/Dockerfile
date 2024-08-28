FROM openjdk:21-slim

ENV MY_APP_NAME libraryProject
ENV DATABASE_URL jdbc:mysql://db:3306/my_database

WORKDIR /app

COPY target/libraryProject-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8040

ENTRYPOINT ["java", "-jar", "app.jar"]






