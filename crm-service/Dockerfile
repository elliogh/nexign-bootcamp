FROM openjdk:11
WORKDIR /app
COPY target/crm-service-0.0.1-SNAPSHOT.jar crm-service.jar
ENTRYPOINT ["java", "-jar", "crm-service.jar"]