package ru.ellio.crmservice.request;

import lombok.Data;

/**
 * Запрос на тарификацию.
 */
@Data
public class BillingRequest {
    private final String action;
}
