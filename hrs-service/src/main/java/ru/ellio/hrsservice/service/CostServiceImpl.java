package ru.ellio.hrsservice.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.ellio.hrsservice.dto.Operation;
import ru.ellio.hrsservice.response.BillingResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CostServiceImpl implements CostService {
    public List<BillingResponse> calculateCost(Resource resource) throws IOException {
        String[] cdrPlus = cdrToArray(resource);

        Map<String, List<Operation>> clientsWithOperations = makeClientsWithOperationsFromCdr(cdrPlus);

        List<BillingResponse> responses = new ArrayList<>();
        for (Map.Entry<String, List<Operation>> client : clientsWithOperations.entrySet()) {
            for (Operation operation : client.getValue()) {
                double defaultMinutePrice = operation.getDefaultMinutePrice();
                double tariffMinutePrice = operation.getTariffMinutePrice();
                boolean incomingFree = operation.isIncomingFree();
                String callType = operation.getCallType();
                long durationInSeconds = Duration.between(operation.getStartTime(), operation.getEndTime()).toSeconds();
                long durationInMinutes = (Duration.between(operation.getStartTime(), operation.getEndTime()).toSeconds()) / 60; // в минутах
                if (durationInMinutes % 60 != 0) durationInMinutes++; // Округляем в большую сторону

                double cost = 0;

                //Проверка на тариф с бесплатными входящими и на входящий звонок
                if (incomingFree && callType.equals("02")) {
                    responses.add(new BillingResponse(client.getKey(), operation.getCallType(), operation.getTariffId(), operation.getStartTime(), operation.getEndTime(), durationInSeconds, cost));
                    continue;
                }

                // Каждую минуту звонка проверяем закончились ли минуты в пакете
                while (durationInMinutes != 0) {
                    durationInMinutes--;
                    if (operation.getMinutesLeft() != 0) {
                        operation.setMinutesLeft(operation.getMinutesLeft() - 1); // Не закончились - вычитаем минуты из пакета
                        cost += tariffMinutePrice;                            // и считаем по тарифу
                    } else {
                        cost += defaultMinutePrice; // Когда закончились, то считаем по обычному стандартному тарифу
                    }
                }
                responses.add(new BillingResponse(client.getKey(), operation.getCallType(), operation.getTariffId(), operation.getStartTime(), operation.getEndTime(), durationInSeconds, cost));

                client.getValue().stream().forEach(op ->
                        op.setMinutesLeft(operation.getMinutesLeft())
                );
            }
        }
        return responses;
    }

    private String[] cdrToArray(Resource resource) throws IOException {
        InputStream inputStream = resource.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().parallel().collect(Collectors.joining("\n"))
                .split("\n");
    }

    private Map<String, List<Operation>> makeClientsWithOperationsFromCdr(String[] cdrPlus) {
        Map<String, List<Operation>> clientsWithOperations = new HashMap<>();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        Arrays.stream(cdrPlus).forEach(line -> {
            String[] arguments = line.split(",");
            String callType = arguments[0];
            String numberPhone = arguments[1];
            LocalDateTime startTime = LocalDateTime.parse(arguments[2].trim(), dateFormat);
            LocalDateTime endTime = LocalDateTime.parse(arguments[3].trim(), dateFormat);
            String tariffId = arguments[4];
            double price = Double.parseDouble(arguments[5]);
            double defaultMinutePrice = Double.parseDouble(arguments[6]);
            double tariffMinutePrice = Double.parseDouble(arguments[7]);
            int minutesLeft = Integer.parseInt(arguments[8]);
            boolean incomingFree = Boolean.parseBoolean(arguments[9]);

            if (!clientsWithOperations.containsKey(numberPhone)) {
                clientsWithOperations.put(numberPhone, new ArrayList<>());
            }

            clientsWithOperations.get(numberPhone).add(new Operation(
                    callType, startTime, endTime, tariffId, price, defaultMinutePrice, tariffMinutePrice, minutesLeft, incomingFree
            ));
        });
        return clientsWithOperations;
    }
}
