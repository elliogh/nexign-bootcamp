package ru.ellio.brtservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ellio.brtservice.response.BillingResponse;

import java.util.List;

@FeignClient(name = "hrs", configuration = FeignConfig.class)
public interface HrsClient {
    @GetMapping("/hrs/calculateCost")
    List<BillingResponse> calculateCost();
}
