package ru.ellio.brtservice.request;

import lombok.Data;

@Data
public class CreateClientRequest {
    private final String numberPhone;
    private final String tariff_id;
    private final double balance;
}
