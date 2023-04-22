package ru.ellio.crmservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ellio.crmservice.clients.BrtClient;
import ru.ellio.crmservice.dto.MoneyDto;
import ru.ellio.crmservice.dto.ReportDto;
import ru.ellio.crmservice.request.PayRequest;

@RestController
@RequestMapping("/abonent")
@AllArgsConstructor
public class AbonentController {
    BrtClient brtClient;

    @PatchMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MoneyDto pay(
            @RequestBody PayRequest payRequest) throws Exception {
        return brtClient.pay(payRequest);
    }

    @GetMapping("report/{numberPhone}")
    public ReportDto report(
            @PathVariable String numberPhone) throws Exception {
        return brtClient.report(numberPhone);
    }

}

