package ru.ellio.brtservice.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;

@Headers("Cache-Control: no-cache")
@FeignClient(name = "cdr-generator")
public interface CdrClient {
    @GetMapping("/cdr/random")
    Resource random();
}
