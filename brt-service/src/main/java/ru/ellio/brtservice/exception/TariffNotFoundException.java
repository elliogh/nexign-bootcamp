package ru.ellio.brtservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TariffNotFoundException extends Exception {
    public TariffNotFoundException(String tariffId) {
        super(String.format("Could not find tariff with tariff_id: %s.", tariffId));
    }
}
