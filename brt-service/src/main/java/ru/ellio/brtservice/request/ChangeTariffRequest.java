package ru.ellio.brtservice.request;

import lombok.Data;

/**
 * Запрос на изменение тарифа.
 */
@Data
public class ChangeTariffRequest {
    private final String numberPhone;
    private final String tariff_id;
}
