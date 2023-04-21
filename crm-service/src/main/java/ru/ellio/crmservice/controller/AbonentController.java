package ru.ellio.crmservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abonent")
public class AbonentController {
    @Autowired
    BrtClient brtClient;

    @GetMapping("/pay")
    public String pay() {
        brtClient.pay();
        return "";
    }

    @GetMapping("/report")
    public String report() {
        return "";
    }

}

