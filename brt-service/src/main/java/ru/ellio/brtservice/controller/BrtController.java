package ru.ellio.brtservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import ru.ellio.brtservice.clients.CdrClient;
import ru.ellio.brtservice.clients.HrsClient;
import ru.ellio.brtservice.dto.*;
import ru.ellio.brtservice.request.ChangeTariffRequest;
import ru.ellio.brtservice.request.CreateClientRequest;
import ru.ellio.brtservice.request.PayRequest;
import ru.ellio.brtservice.response.BillingResponse;
import ru.ellio.brtservice.service.ClientService;
import ru.ellio.brtservice.service.GeneratorService;

import java.io.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/brt")
public class BrtController {
    ClientService clientService;
    CdrClient cdrClient;
    HrsClient hrsClient;
    GeneratorService generatorService;

    @PatchMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MoneyDto pay(
            @RequestBody PayRequest payRequest) throws Exception {
        return clientService.addMoney(payRequest);
    }

    @GetMapping("report/{numberPhone}")
    public ReportDto report(
            @PathVariable String numberPhone) throws Exception {
        return clientService.report(numberPhone);
    }

    @PatchMapping(value = "/changeTariff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientTariffDto changeTariff(
            @RequestBody ChangeTariffRequest changeTariffRequest) throws Exception {
        return clientService.changeTariff(changeTariffRequest);
    }

    @PostMapping(value = "/abonent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto createClient(
            @RequestBody CreateClientRequest createClientRequest) throws Exception {
        return clientService.createClient(createClientRequest);
    }

    @PatchMapping(value = "/billing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BillingDto billing(
            ) throws IOException {
        Resource resource = cdrClient.random();
        generatorService.generateCdrPlus(resource);
        List<BillingResponse> billingResponse = hrsClient.calculateCost();

        return clientService.billing(billingResponse);
    }

    @GetMapping("/cdrPlus")
    public Resource cdrPlus() throws IOException {
        File file = new File("cdrPlus/cdrPlus.txt");
        byte[] buffer = new byte[(int) file.length()];

        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(buffer);
        }

        return new ByteArrayResource(buffer);
    }

}
