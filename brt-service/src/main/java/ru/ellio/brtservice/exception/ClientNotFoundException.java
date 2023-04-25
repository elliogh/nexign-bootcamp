package ru.ellio.brtservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends Exception {
    public ClientNotFoundException(String numberPhone) {
        super(String.format("Could not find client with numberPhone: %s.", numberPhone));
    }
}
