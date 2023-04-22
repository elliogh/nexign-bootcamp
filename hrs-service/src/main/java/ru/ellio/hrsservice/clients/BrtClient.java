package ru.ellio.hrsservice.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Клиент для обращений к brt-service.
 */
@Headers("Cache-Control: no-cache")
@FeignClient(name = "brt")
public interface BrtClient {
    @GetMapping("/brt/cdrPlus")
    Resource cdrPlus();
}
