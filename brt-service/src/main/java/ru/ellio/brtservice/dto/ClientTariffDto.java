package ru.ellio.brtservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientTariffDto {
    private final long id;
    private final String numberPhone;
    private final String tariff_id;
}
