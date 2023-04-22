package ru.ellio.brtservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ellio.brtservice.dto.*;
import ru.ellio.brtservice.request.ChangeTariffRequest;
import ru.ellio.brtservice.request.CreateClientRequest;
import ru.ellio.brtservice.request.PayRequest;
import ru.ellio.brtservice.response.BillingResponse;
import ru.ellio.brtservice.model.Client;
import ru.ellio.brtservice.model.Operation;
import ru.ellio.brtservice.model.Tariff;
import ru.ellio.brtservice.repository.ClientRepository;
import ru.ellio.brtservice.repository.TariffRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    ClientRepository clientRepository;
    TariffRepository tariffRepository;
    Mapper mapper;

    @Override
    public MoneyDto addMoney(PayRequest payRequest) throws Exception {
        Client client = clientRepository
                .findClientByNumberPhone(payRequest.getNumberPhone())
                .orElseThrow(() -> new Exception("Такого абонента нет"));
        client.setBalance(client.getBalance() + payRequest.getMoney());
        clientRepository.save(client);
        return mapper.toMoneyDto(client);
    }

    @Override
    public ReportDto report(String numberPhone) throws Exception {
        Client client = clientRepository
                .findClientByNumberPhone(numberPhone)
                .orElseThrow(() -> new Exception("Такого абонента нет"));
        return mapper.toReportDto(client);
    }

    @Override
    public ClientTariffDto changeTariff(ChangeTariffRequest changeTariffRequest) throws Exception {
        Tariff tariff = tariffRepository
                .findAllByTariffId(changeTariffRequest.getTariff_id())
                .orElseThrow(() -> new Exception("Такого тарифа нет"));
        Client client = clientRepository
                .findClientByNumberPhone(changeTariffRequest.getNumberPhone())
                .orElseThrow(() -> new Exception("Такого абонента нет"));
        client.setTariff(tariff);
        clientRepository.save(client);
        return mapper.toClientTariffDto(client);
    }

    @Override
    public ClientDto createClient(CreateClientRequest createClientRequest) throws Exception {
        boolean isClient = clientRepository.findClientByNumberPhone(createClientRequest.getNumberPhone()).isPresent();
        if (isClient) throw new Exception("Абонент с таким телефоном существует");
        Tariff tariff = tariffRepository
                .findAllByTariffId(createClientRequest.getTariff_id())
                .orElseThrow(() -> new Exception("Такого тарифа нет"));
        Client client = Client.builder()
                .numberPhone(createClientRequest.getNumberPhone())
                .tariff(tariff)
                .balance(createClientRequest.getBalance())
                .monetaryUnit("rubles")
                .build();
        clientRepository.save(client);
        return mapper.toClientDto(client);
    }

    @Override
    public BillingDto billing(List<BillingResponse> billingResponses) {

        Map<String, List<Operation>> phonesWithOperations = new HashMap<>(); // Мапа телефон -> операции

        // Каждый response кладем в мапу
        billingResponses.stream().forEach(response -> {
            if (!phonesWithOperations.containsKey(response.getNumberPhone())) {
                phonesWithOperations.put(response.getNumberPhone(), new ArrayList<>());
            }

            phonesWithOperations.get(response.getNumberPhone())
                    .add(makeOperation(response));
        });

        List<Client> clients = clientRepository.findAll(); // все клиенты
        clear(clients);

        // Для каждого клиента, если он есть в billingResponses
        clients.stream().forEach(client -> {
            client.setPayload(new ArrayList<>());
            if (phonesWithOperations.containsKey(client.getNumberPhone())) {
                client.getPayload().addAll(phonesWithOperations.get(client.getNumberPhone())); // добавляем операции с их стоимостями
                client.setTotalCost(calculateTotalCost(client));                               // считаем и устонавливаем полную стоимость за период
                client.setBalance(client.getBalance() - client.getTotalCost());                // меняем баланс
            }
        });

        clientRepository.saveAll(clients); // сохраняем пользователей с операциями

        return BillingDto.builder()
                        .numbers(clients.stream()
                                .filter(client -> phonesWithOperations.containsKey(client.getNumberPhone()))
                                .map(client ->  mapper.toClientBalanceDto(client))
                                .collect(Collectors.toList())
                        ).build();

    }

    private Operation makeOperation(BillingResponse response) {
        return Operation.builder()
                .callType(response.getCallType())
                .startTime(response.getStartTime())
                .endTime(response.getEndTime())
                .duration(response.getDuration())
                .cost(response.getCost())
                .build();
    }

    private double calculateTotalCost(Client client) {
        return client.getPayload().stream()
                .mapToDouble(Operation::getCost)
                .sum() + client.getTariff().getPrice();
    }

    private void clear(List<Client> clients) {
        clients.forEach(client -> client.setPayload(new ArrayList<>()));
        clientRepository.saveAll(clients);
    }
}
