FROM openjdk:8-jdk-alpine
ADD target/profile*.jar /app.jar
CMD ["java", "-jar", "/app.jar","--spring.profiles.active=prod"]
EXPOSE 8082