package ru.ellio.hrsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HrsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrsServiceApplication.class, args);
    }

}
