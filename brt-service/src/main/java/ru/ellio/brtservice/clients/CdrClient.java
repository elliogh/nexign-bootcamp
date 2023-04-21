package ru.ellio.brtservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cdr-generator", configuration = FeignConfig.class)
public interface CdrClient {
    @GetMapping("/cdr/random")
    Resource random();
}
