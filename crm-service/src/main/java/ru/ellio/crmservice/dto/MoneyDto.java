package ru.ellio.crmservice.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO для ответа на пополнение счета абонента.
 */
@Data
@Builder
public class MoneyDto {
    private final long id;
    private final String numberPhone;
    private final double money;
}
