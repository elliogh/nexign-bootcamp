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

    /**
     * Endpoint для смены тарифа.
     *
     * @param changeTariffRequest запрос на смену тарифа абонента
     * @return ответ на смену тарифа
     * @throws Exception тариф не найден, абонент не найден
     * @see ChangeTariffRequest
     * @see ClientTariffDto
     */
    @PatchMapping(value = "/changeTariff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientTariffDto changeTariff(
            @RequestBody ChangeTariffRequest changeTariffRequest) throws Exception {
        return brtClient.changeTariff(changeTariffRequest);
    }

    /**
     * Endpoint для добавления нового абонента.
     *
     * @param createClientRequest запрос на создание нового абонента.
     * @return ответ на созадние нового абонента
     * @throws Exception абонент с таким телефоном существует, тариф не найден
     * @see CreateClientRequest
     * @see ClientDto
     */
    @PostMapping(value = "/abonent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto createClient(
            @RequestBody CreateClientRequest createClientRequest) throws Exception {
        return brtClient.createClient(createClientRequest);
    }

    /**
     * Endpoint для выполнения тарификации.
     *
     * @return ответ на выполнение тарификации
     * @throws IOException ошибка генерации файла
     * @see BillingDto
     */
    @PatchMapping(value = "/billing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BillingDto billing() throws IOException {
        return brtClient.billing();
    }
}
