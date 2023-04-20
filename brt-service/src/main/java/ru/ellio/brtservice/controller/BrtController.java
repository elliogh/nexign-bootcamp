package ru.ellio.brtservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ellio.brtservice.service.ClientService;

@RestController
@RequestMapping("/brt")
public class BrtController {
    ClientService clientService;

    public BrtController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String init() throws Exception {
        clientService.addMoney("73734435247", 123);
        return "clientService.report().toString()";
    }
}
