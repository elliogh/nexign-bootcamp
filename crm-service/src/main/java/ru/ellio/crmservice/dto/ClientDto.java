package ru.ellio.crmservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {
    private final String numberPhone;
    private final String tariff_id;
    private final double balance;
}
