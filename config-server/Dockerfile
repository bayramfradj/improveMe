FROM openjdk:8-jdk-alpine
ADD target/config-server*.jar /app.jar
COPY /home/bayram/Projects/improveME/config/* . /home/bayram/Projects/improveME/config/
CMD ["git", "init", "/home/bayram/Projects/improveME/config/"]
CMD ["git", "add ",".", "/home/bayram/Projects/improveME/config/"]
CMD ["git", "commit ","-m","tt", "/home/bayram/Projects/improveME/config/"]
CMD ["java", "-jar", "/app.jar","--spring.profiles.active=prod"]
EXPOSE 8888