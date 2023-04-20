package ru.ellio.brtservice.service;


import ru.ellio.brtservice.model.Client;

import java.util.List;

public interface ClientService {
//     List<Client> findAllBy();
     void addMoney(String numberPhone, double money) throws Exception;
     Client report(String numberPhone) throws Exception;
     void changeTariff(String numberPhone, String tariffId) throws Exception;
     void createClient(String numberPhone, String tariffId, long balance) throws Exception;

     List<Client> billing();
}
