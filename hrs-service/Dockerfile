FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/hrs-service-0.0.1-SNAPSHOT.jar hrs-service.jar
ENTRYPOINT ["java", "-jar", "hrs-service.jar"]