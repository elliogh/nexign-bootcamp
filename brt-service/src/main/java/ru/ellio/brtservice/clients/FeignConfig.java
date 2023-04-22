package ru.ellio.brtservice.clients;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация для Feign.
 */
@Configuration
public class FeignConfig {

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(
                3000, //connectTimeoutMillis
                3000 // readTimeoutMillis
        );
    }
}
