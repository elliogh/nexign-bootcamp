package ru.ellio.crmservice.controller;

import lombok.AllArgsConstructor;
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

    /**
     * Endpoint для пополнения баланса абонента.
     *
     * @param payRequest запрос на пополнение баланса
     * @return ответ на пополнение баланса
     * @throws Exception абонент не найден
     * @see PayRequest
     * @see MoneyDto
     */
    @PatchMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MoneyDto pay(
            @RequestBody PayRequest payRequest) throws Exception {
        return brtClient.pay(payRequest);
    }

    /**
     * Endpoint для генерации отчета по определенному абоненту.
     *
     * @param numberPhone номер телефона абонента
     * @return отчет
     * @throws Exception абонент не найден
     * @see ReportDto
     */
    @GetMapping("report/{numberPhone}")
    public ReportDto report(
            @PathVariable String numberPhone) throws Exception {
        return brtClient.report(numberPhone);
    }

}

