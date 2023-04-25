package ru.ellio.hrsservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ellio.hrsservice.clients.BrtClient;
import ru.ellio.hrsservice.response.BillingResponse;
import ru.ellio.hrsservice.service.CostService;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/hrs")
public class HrsController {
    BrtClient brtClient;
    CostService costService;

    /**
     * Endpoint для рассчета цены звонка.
     *
     * @return список операций с посчитанной ценой
     * @throws IOException ошибка чтения файла
     */
    @GetMapping("/calculateCost")
    public List<BillingResponse> calculateCost() throws IOException {
        Resource resource = brtClient.cdrPlus();
        log.info("File cdrPlus was received");
        return costService.calculateCost(resource);
    }
}
