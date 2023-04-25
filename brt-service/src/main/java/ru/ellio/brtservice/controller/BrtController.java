package ru.ellio.brtservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import ru.ellio.brtservice.clients.CdrClient;
import ru.ellio.brtservice.clients.HrsClient;
import ru.ellio.brtservice.dto.*;
import ru.ellio.brtservice.exception.ClientExistsException;
import ru.ellio.brtservice.exception.ClientNotFoundException;
import ru.ellio.brtservice.exception.TariffNotFoundException;
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

    /**
     * Endpoint для пополнения баланса абонента.
     *
     * @param payRequest запрос на пополнение баланса
     * @return ответ на пополнение баланса
     * @throws ClientNotFoundException абонент не найден
     * @see PayRequest
     * @see MoneyDto
     */
    @PatchMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MoneyDto pay(
            @RequestBody PayRequest payRequest) throws ClientNotFoundException {
        return clientService.addMoney(payRequest);
    }

    /**
     * Endpoint для генерации отчета по определенному абоненту.
     *
     * @param numberPhone номер телефона абонента
     * @return отчет
     * @throws ClientNotFoundException абонент не найден
     * @see ReportDto
     */
    @GetMapping("report/{numberPhone}")
    public ReportDto report(
            @PathVariable String numberPhone) throws ClientNotFoundException {
        return clientService.report(numberPhone);
    }

    /**
     * Endpoint для смены тарифа.
     *
     * @param changeTariffRequest запрос на смену тарифа абонента
     * @return ответ на смену тарифа
     * @throws TariffNotFoundException тариф не найден
     * @throws ClientNotFoundException абонент не найден
     * @see ChangeTariffRequest
     * @see ClientTariffDto
     */
    @PatchMapping(value = "/changeTariff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientTariffDto changeTariff(
            @RequestBody ChangeTariffRequest changeTariffRequest) throws TariffNotFoundException, ClientNotFoundException {
        return clientService.changeTariff(changeTariffRequest);
    }

    /**
     * Endpoint для добавления нового абонента.
     *
     * @param createClientRequest запрос на создание нового абонента.
     * @return ответ на созадние нового абонента
     * @throws ClientExistsException абонент с таким телефоном существует
     * @throws TariffNotFoundException тариф не найден
     * @see CreateClientRequest
     * @see ClientDto
     */
    @PostMapping(value = "/abonent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto createClient(
            @RequestBody CreateClientRequest createClientRequest) throws ClientExistsException, TariffNotFoundException {
        return clientService.createClient(createClientRequest);
    }

    /**
     * Endpoint для выполнения тарификации.
     *
     * @return ответ на выполнение тарификации
     * @throws IOException ошибка генерации файла
     * @see BillingDto
     */
    @PatchMapping(value = "/billing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BillingDto billing(
    ) throws IOException {
        Resource resource = cdrClient.random();
        generatorService.generateCdrPlus(resource);
        List<BillingResponse> billingResponse = hrsClient.calculateCost();

        return clientService.billing(billingResponse);
    }

    /**
     * Endpoint для получения файла CDR+ с тарифом.
     * @return файл
     * @throws IOException ошибка чтения файла
     */
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
