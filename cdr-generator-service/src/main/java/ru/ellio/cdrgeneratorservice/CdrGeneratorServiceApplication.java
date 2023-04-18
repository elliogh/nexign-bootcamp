package ru.ellio.cdrgeneratorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CdrGeneratorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdrGeneratorServiceApplication.class, args);
    }

}
