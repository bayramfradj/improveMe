FROM openjdk:8-jdk-alpine
ADD target/evaluation*.jar /app.jar
CMD ["java", "-jar", "/app.jar","--spring.profiles.active=prod"]
EXPOSE 8081