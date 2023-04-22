package ru.ellio.crmservice.request;

import lombok.Data;

/**
 * Запрос на пополнение счета абонента.
 */
@Data
public class PayRequest {
    private final String numberPhone;
    private final double money;
}
