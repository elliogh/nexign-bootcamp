package ru.ellio.crmservice.request;

import lombok.Data;

/**
 * Запрос на создание нового абонента.
 */
@Data
public class CreateClientRequest {
    private final String numberPhone;
    private final String tariff_id;
    private final double balance;
}
