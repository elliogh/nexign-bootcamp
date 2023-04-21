package ru.ellio.hrsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class HrsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrsServiceApplication.class, args);
    }

}
