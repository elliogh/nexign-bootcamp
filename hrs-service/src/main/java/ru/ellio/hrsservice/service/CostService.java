package ru.ellio.hrsservice.service;

import org.springframework.core.io.Resource;
import ru.ellio.hrsservice.response.BillingResponse;

import java.io.IOException;
import java.util.List;

public interface CostService {
    List<BillingResponse> calculateCost(Resource resource) throws IOException;
}
