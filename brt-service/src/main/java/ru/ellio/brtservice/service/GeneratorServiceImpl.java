package ru.ellio.brtservice.service;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.ellio.brtservice.model.Client;
import ru.ellio.brtservice.model.Tariff;
import ru.ellio.brtservice.repository.ClientRepository;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {
    ClientRepository clientRepository;

    public File generateCdrPlus(Resource resource) throws IOException {
        createDirectory("cdrPlus");

        Map<String, Client> clients = clientRepository
                .findAll().stream()
                .collect(Collectors.toMap(Client::getNumberPhone, Function.identity()));

        String[] cdr = cdrToArray(resource);

        StringBuilder cdrPlus = new StringBuilder();

        Arrays.stream(cdr).forEach(line -> {
            String numberPhone = line.split(",")[1];

            if (clients.containsKey(numberPhone)) {
                Client client = clients.get(numberPhone);
                if (client.getBalance() > 0) {            // добавляем в новый cdr, если баланс > 0
                    cdrPlus.append(line)
                            .append(appendTariff(client.getTariff())); // вместе с тарифом

                }
            }

        });

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                "cdrPlus/cdrPlus.txt"))) {
            writer.write(cdrPlus.toString());
        }
        return new File(String.format("%s/cdrPlus%s", "cdrPlus", ".txt"));
    }

    private void createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private String[] cdrToArray(Resource resource) throws IOException {
        InputStream inputStream = resource.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().parallel().collect(Collectors.joining("\n"))
                .split("\n");
    }

    private String appendTariff(Tariff tariff) {
        return new StringBuilder().append(",").append(tariff.getTariffId())
                .append(",").append(tariff.getPrice())
                .append(",").append(tariff.getDefaultMinutePrice())
                .append(",").append(tariff.getTariffMinutePrice())
                .append(",").append(tariff.getMinutesLeft())
                .append(",").append(tariff.isIncomingFree()).append("\n")
                .toString();
    }
}
