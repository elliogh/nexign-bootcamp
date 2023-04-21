package ru.ellio.hrsservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ellio.hrsservice.clients.BrtClient;
import ru.ellio.hrsservice.response.BillingResponse;
import ru.ellio.hrsservice.service.CostService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hrs")
public class HrsController {
    BrtClient brtClient;
    CostService costService;

    @GetMapping("/calculateCost")
    public List<BillingResponse> calculateCost() throws IOException {
        Resource resource = brtClient.cdrPlus();
        return costService.calculateCost(resource);
    }
}
