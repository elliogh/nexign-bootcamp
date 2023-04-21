package ru.ellio.brtservice.service;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public interface GeneratorService {
    File generateCdrPlus(Resource resource) throws IOException;
}
