package ru.ellio.cdrgeneratorservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ellio.cdrgeneratorservice.service.Generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/cdr")
@RequiredArgsConstructor
public class CdrController {
    private final Generator reportGenerator;

    @GetMapping("/random")
    public Resource randomCdr() throws IOException {
        File file = reportGenerator.generate();
        log.info("CDR was successfully generated");
        byte[] buffer = new byte[(int) file.length()];

        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(buffer);
        }

        return new ByteArrayResource(buffer);
    }
}
