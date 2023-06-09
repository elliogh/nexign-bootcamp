package ru.ellio.brtservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * DTO для ответа на тарификацию - список абонентов с их балансом.
 */
@Data
@Builder
public class BillingDto {
    private final List<ClientBalanceDto> numbers;

    @JsonCreator
    public BillingDto(@JsonProperty("numbers") List<ClientBalanceDto> numbers) {
        this.numbers = numbers;
    }
}
