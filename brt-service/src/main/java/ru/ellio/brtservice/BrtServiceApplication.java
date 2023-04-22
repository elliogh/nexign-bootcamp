package ru.ellio.brtservice;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import ru.ellio.brtservice.controller.BrtController;
import ru.ellio.brtservice.dto.BillingDto;
import ru.ellio.brtservice.request.BillingRequest;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@EnableEurekaClient
@EnableFeignClients
@AllArgsConstructor
@SpringBootApplication
public class BrtServiceApplication {
    BrtController brtController;

    public static void main(String[] args) {
        SpringApplication.run(BrtServiceApplication.class, args);
    }

    // Выполнение тарификации при запуске приложения
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        BillingDto result = null;
        while (result == null) {
            try {
                Thread.sleep(4000);
                result = brtController.billing();
            } catch (Exception ignored) {}
        }
    }
}
