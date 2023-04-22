package ru.ellio.brtservice.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ellio.brtservice.response.BillingResponse;

import java.util.List;

/**
 * Клиент для обращений к hrs-service.
 */
@Headers("Cache-Control: no-cache")
@FeignClient(name = "hrs", configuration = FeignConfig.class)
public interface HrsClient {

    @GetMapping("/hrs/calculateCost")
    List<BillingResponse> calculateCost();
}
