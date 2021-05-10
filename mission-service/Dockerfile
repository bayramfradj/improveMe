FROM openjdk:8-jdk-alpine
ADD target/mission-service-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar","--spring.profiles.active=prod"]
EXPOSE 8083