package ru.ellio.crmservice.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO для ответа на изменение тарифа.
 */
@Data
@Builder
public class ClientTariffDto {
    private final long id;
    private final String numberPhone;
    private final String tariff_id;
}
