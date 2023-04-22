package ru.ellio.crmservice.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ellio.crmservice.dto.*;
import ru.ellio.crmservice.request.ChangeTariffRequest;
import ru.ellio.crmservice.request.CreateClientRequest;
import ru.ellio.crmservice.request.PayRequest;

import java.io.IOException;
import java.util.List;

/**
 * Клиент для обращений к brt-service.
 */
@Headers("Cache-Control: no-cache")
@FeignClient(name = "brt")
public interface BrtClient {
    @PatchMapping(value = "brt/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    MoneyDto pay(
            @RequestBody PayRequest payRequest) throws Exception;

    @GetMapping("brt/report/{numberPhone}")
    ReportDto report(
            @PathVariable String numberPhone) throws Exception;

    @PatchMapping(value = "brt/changeTariff", consumes = MediaType.APPLICATION_JSON_VALUE)
    ClientTariffDto changeTariff(
            @RequestBody ChangeTariffRequest changeTariffRequest) throws Exception;

    @PostMapping(value = "brt/abonent", consumes = MediaType.APPLICATION_JSON_VALUE)
    ClientDto createClient(
            @RequestBody CreateClientRequest createClientRequest) throws Exception ;

    @PatchMapping(value = "brt/billing", consumes = MediaType.APPLICATION_JSON_VALUE)
    BillingDto billing(
    ) throws IOException;
}
