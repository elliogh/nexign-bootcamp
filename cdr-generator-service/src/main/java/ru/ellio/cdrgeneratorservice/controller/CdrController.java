package ru.ellio.cdrgeneratorservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ellio.cdrgeneratorservice.service.Generator;

@RestController
@RequestMapping("/cdr")
@RequiredArgsConstructor
public class CdrController {
    private final Generator reportGenerator;

    @GetMapping("/random")
    public String randomCdr() {
        return reportGenerator.generate();
    }
}
