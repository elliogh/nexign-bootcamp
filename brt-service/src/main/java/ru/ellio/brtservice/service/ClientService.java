package ru.ellio.brtservice.service;


import ru.ellio.brtservice.dto.*;
import ru.ellio.brtservice.request.ChangeTariffRequest;
import ru.ellio.brtservice.request.CreateClientRequest;
import ru.ellio.brtservice.request.PayRequest;
import ru.ellio.brtservice.response.BillingResponse;

import java.util.List;

public interface ClientService {
     MoneyDto addMoney(PayRequest payRequest) throws Exception;
     ReportDto report(String numberPhone) throws Exception;
     ClientTariffDto changeTariff(ChangeTariffRequest changeTariffRequest) throws Exception;
     ClientDto createClient(CreateClientRequest createClientRequest) throws Exception;

     BillingDto billing(List<BillingResponse> billingResponses);
}
