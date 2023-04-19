package ru.ellio.brtservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BrtServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrtServiceApplication.class, args);
    }

}
