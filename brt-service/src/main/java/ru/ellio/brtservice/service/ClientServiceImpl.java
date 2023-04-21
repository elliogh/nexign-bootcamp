package ru.ellio.brtservice.service;

import org.springframework.stereotype.Service;
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

@Service
public class ClientServiceImpl implements ClientService {
    ClientRepository clientRepository;
    TariffRepository tariffRepository;

    public ClientServiceImpl(ClientRepository clientRepository, TariffRepository tariffRepository) {
        this.clientRepository = clientRepository;
        this.tariffRepository = tariffRepository;
    }

    @Override
    public void addMoney(String numberPhone, double money) throws Exception {
        Client client = clientRepository
                .findClientByNumberPhone(numberPhone)
                .orElseThrow(() -> new Exception("Такого абонента нет"));
        client.setBalance(client.getBalance() + money);
        clientRepository.save(client);
    }

    @Override
    public Client report(String numberPhone) throws Exception {
        return clientRepository
                .findClientByNumberPhone(numberPhone)
                .orElseThrow(() -> new Exception("Такого абонента нет"));
    }

    @Override
    public void changeTariff(String numberPhone, String tariffId) throws Exception {
        Tariff tariff = tariffRepository
                .findAllByTariffId(tariffId)
                .orElseThrow(() -> new Exception("Такого тарифа нет"));
        Client client = clientRepository
                .findClientByNumberPhone(numberPhone)
                .orElseThrow(() -> new Exception("Такого абонента нет"));
        client.setTariff(tariff);
        clientRepository.save(client);
    }

    @Override
    public void createClient(String numberPhone, String tariffId, long balance) throws Exception {
        Tariff tariff = tariffRepository
                .findAllByTariffId(tariffId)
                .orElseThrow(() -> new Exception("Такого тарифа нет"));
        Client client = Client.builder()
                .numberPhone(numberPhone)
                .tariff(tariff)
                .balance(balance)
                .monetaryUnit("rubles")
                .build();
        clientRepository.save(client);
    }

    @Override
    public void billing(List<BillingResponse> billingResponses) {

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
