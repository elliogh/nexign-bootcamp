package ru.ellio.brtservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientExistsException extends Exception {
    public ClientExistsException(String numberPhone) {
        super(String.format("Client already exists with numberPhone: %s.", numberPhone));
    }
}
