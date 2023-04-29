FROM openjdk:11
WORKDIR /app
COPY target/cdr-generator-service-0.0.1-SNAPSHOT.jar cdr-generator-service.jar
ENTRYPOINT ["java", "-jar", "cdr-generator-service.jar"]