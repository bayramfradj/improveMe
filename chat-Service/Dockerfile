FROM openjdk:8-jdk-alpine
ADD target/chat-Service-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar","--spring.profiles.active=prod"]
EXPOSE 8085