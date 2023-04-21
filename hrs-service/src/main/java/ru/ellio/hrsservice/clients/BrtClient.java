package ru.ellio.hrsservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "brt")
public interface BrtClient {
    @GetMapping("/brt/cdrPlus")
    Resource cdrPlus();
}
