package ru.ellio.crmservice.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @PatchMapping("/chaneTariff")
    public String changeTariff() {
        return "";
    }

    @PostMapping("/abonent")
    public String createAbonent() {
        return "";
    }

    @PatchMapping("/billing")
    public String billing() {
        return "";
    }
}
