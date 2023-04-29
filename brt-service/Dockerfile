FROM openjdk:11
WORKDIR /app
COPY target/brt-service-0.0.1-SNAPSHOT.jar brt-service.jar
ENTRYPOINT ["java", "-jar", "brt-service.jar"]