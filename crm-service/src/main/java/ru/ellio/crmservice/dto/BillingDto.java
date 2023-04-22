package ru.ellio.crmservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BillingDto {
    private final List<ClientBalanceDto> numbers;
}
