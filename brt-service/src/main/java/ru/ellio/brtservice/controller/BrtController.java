package ru.ellio.brtservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import ru.ellio.brtservice.clients.CdrClient;
import ru.ellio.brtservice.clients.HrsClient;
import ru.ellio.brtservice.response.BillingResponse;
import ru.ellio.brtservice.service.ClientService;
import ru.ellio.brtservice.service.GeneratorService;

import java.io.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/brt")
public class BrtController {
    ClientService clientService;
    CdrClient cdrClient;
    HrsClient hrsClient;
    GeneratorService generatorService;

    @GetMapping("/pay")
    public void pay() throws Exception {
        clientService.addMoney("73734435247", 124);
    }

    @GetMapping("/billing")
    public String billing() throws IOException {
        Resource resource = cdrClient.random();
        generatorService.generateCdrPlus(resource);
        List<BillingResponse> billingResponse = hrsClient.calculateCost();
        clientService.billing(billingResponse);
        return "result";
    }

    @GetMapping("/cdrPlus")
    public Resource cdrPlus() throws IOException {
        File file = new File("cdrPlus/cdrPlus.txt");
        byte[] buffer = new byte[(int) file.length()];

        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(buffer);
        }

        Resource resource = new ByteArrayResource(buffer);
        return resource;
    }

}
