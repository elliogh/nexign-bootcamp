package ru.ellio.crmservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abonent")
public class AbonentController {

    @PatchMapping("/pay")
    public String pay() {
        return "";
    }

    @GetMapping("/report")
    public String report() {
        return "";
    }

}
