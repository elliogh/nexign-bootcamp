#!/bin/bash

#cd eureka-server
#./mvnw clean package
#cd ../cdr-generator-service
#./mvnw clean package
#cd ../hrs-service
#./mvnw clean package
#cd ../brt-service
#./mvnw clean package
#cd ../crm-service
#./mvnw clean package

#cd ../
#
docker build -t eureka-server eureka-server/.
docker build -t cdr-generator-service cdr-generator-service/.
docker build -t hrs-service hrs-service/.
docker build -t brt-service brt-service/.
docker build -t crm-service crm-service/.

#docker run -p 8761:8761 -d eureka-server
#docker run -p 8084:8084 -d cdr-generator-service
#docker run -p 8086:8086 -d hrs-service
#docker run -p 8085:8085 -d brt-service
#docker run -p 8090:8090 -d crm-service