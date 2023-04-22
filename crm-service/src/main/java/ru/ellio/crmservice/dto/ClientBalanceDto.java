package ru.ellio.crmservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientBalanceDto {
    private final String numberPhone;
    private final double balance;
}
