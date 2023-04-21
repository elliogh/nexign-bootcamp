package ru.ellio.crmservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "brt")
public interface BrtClient {
    @GetMapping("/brt/pay")
    void pay();
}
