package ru.ellio.crmservice.request;

import lombok.Data;

@Data
public class PayRequest {
    private final String numberPhone;
    private final double money;
}
