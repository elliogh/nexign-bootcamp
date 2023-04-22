package ru.ellio.crmservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ellio.crmservice.clients.BrtClient;
import ru.ellio.crmservice.dto.BillingDto;
import ru.ellio.crmservice.dto.ClientDto;
import ru.ellio.crmservice.dto.ClientTariffDto;
import ru.ellio.crmservice.request.ChangeTariffRequest;
import ru.ellio.crmservice.request.CreateClientRequest;

import java.io.IOException;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {
    BrtClient brtClient;

    @PatchMapping(value = "/changeTariff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientTariffDto changeTariff(
            @RequestBody ChangeTariffRequest changeTariffRequest) throws Exception {
        return brtClient.changeTariff(changeTariffRequest);
    }

    @PostMapping(value = "/abonent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto createClient(
            @RequestBody CreateClientRequest createClientRequest) throws Exception {
        return brtClient.createClient(createClientRequest);
    }

    @PatchMapping(value = "/billing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BillingDto billing() throws IOException {
        return brtClient.billing();
    }
}
