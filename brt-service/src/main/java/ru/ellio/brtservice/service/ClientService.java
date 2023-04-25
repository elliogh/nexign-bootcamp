package ru.ellio.brtservice.service;


import ru.ellio.brtservice.dto.*;
import ru.ellio.brtservice.exception.ClientExistsException;
import ru.ellio.brtservice.exception.ClientNotFoundException;
import ru.ellio.brtservice.exception.TariffNotFoundException;
import ru.ellio.brtservice.request.ChangeTariffRequest;
import ru.ellio.brtservice.request.CreateClientRequest;
import ru.ellio.brtservice.request.PayRequest;
import ru.ellio.brtservice.response.BillingResponse;

import java.util.List;

public interface ClientService {
     MoneyDto addMoney(PayRequest payRequest) throws ClientNotFoundException;
     ReportDto report(String numberPhone) throws ClientNotFoundException;
     ClientTariffDto changeTariff(ChangeTariffRequest changeTariffRequest) throws TariffNotFoundException, ClientNotFoundException;
     ClientDto createClient(CreateClientRequest createClientRequest) throws ClientExistsException, TariffNotFoundException;

     BillingDto billing(List<BillingResponse> billingResponses);
}
