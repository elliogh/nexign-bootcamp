package ru.ellio.brtservice.service;

import org.springframework.stereotype.Service;
import ru.ellio.brtservice.model.Client;
import ru.ellio.brtservice.model.Tariff;
import ru.ellio.brtservice.repository.ClientRepository;
import ru.ellio.brtservice.repository.TariffRepository;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    ClientRepository clientRepository;
    TariffRepository tariffRepository;

    public ClientServiceImpl(ClientRepository clientRepository, TariffRepository tariffRepository) {
        this.clientRepository = clientRepository;
        this.tariffRepository = tariffRepository;
    }

    @Override
    public void addMoney(String numberPhone, long money) {
        clientRepository
                .findClientByNumberPhone(numberPhone)
                .ifPresent(user -> {
                    user.setBalance(user.getBalance() + money);
                    clientRepository.save(user);
                });
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
                .build();
        clientRepository.save(client);
    }

    @Override
    public List<Client> billing() {
        return clientRepository.findAll();
    }
}
