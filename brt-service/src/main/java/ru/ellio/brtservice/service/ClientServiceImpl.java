package ru.ellio.brtservice.service;

import org.springframework.stereotype.Service;
import ru.ellio.brtservice.model.Client;
import ru.ellio.brtservice.repository.ClientRepository;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAllBy() {
        return clientRepository.findAllBy();
    }

//    public void save(Client client) {
//        clientRepository.save()
//    }

}
