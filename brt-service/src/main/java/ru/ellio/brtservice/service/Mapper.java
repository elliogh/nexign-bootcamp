package ru.ellio.brtservice.service;

import org.springframework.stereotype.Component;
import ru.ellio.brtservice.dto.*;
import ru.ellio.brtservice.model.Client;
import ru.ellio.brtservice.model.Operation;

import java.util.stream.Collectors;

@Component
public class Mapper {
    public MoneyDto toMoneyDto(Client client) {
        return MoneyDto.builder()
                .id(client.getId())
                .numberPhone(client.getNumberPhone())
                .money(client.getBalance())
                .build();
    }

    public ReportDto toReportDto(Client client) {
        return ReportDto.builder()
                .id(client.getId())
                .numberPhone(client.getNumberPhone())
                .tariffIndex(client.getTariff().getTariffId())
                .payload(client.getPayload().stream()
                        .map(this::toOperationDto)
                        .collect(Collectors.toList()))
                .totalCost(client.getTotalCost())
                .monetaryUnit(client.getMonetaryUnit())
                .build();
    }

    public OperationDto toOperationDto(Operation operation) {
        return OperationDto.builder()
                .callType(operation.getCallType())
                .startTime(operation.getStartTime())
                .endTime(operation.getEndTime())
                .duration(operation.getDuration())
                .cost(operation.getCost())
                .build();
    }

    public ClientTariffDto toClientTariffDto(Client client) {
        return ClientTariffDto.builder()
                .id(client.getId())
                .numberPhone(client.getNumberPhone())
                .tariff_id(client.getTariff().getTariffId())
                .build();
    }

    public ClientDto toClientDto(Client client) {
        return ClientDto.builder()
                .numberPhone(client.getNumberPhone())
                .tariff_id(client.getTariff().getTariffId())
                .balance(client.getBalance())
                .build();
    }

    public ClientBalanceDto toClientBalanceDto(Client client) {
        return ClientBalanceDto.builder()
                .numberPhone(client.getNumberPhone())
                .balance(client.getBalance())
                .build();
    }
}
