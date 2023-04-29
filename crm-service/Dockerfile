FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/crm-service-0.0.1-SNAPSHOT.jar crm-service.jar
ENTRYPOINT ["java", "-jar", "crm-service.jar"]