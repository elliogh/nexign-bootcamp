package ru.ellio.crmservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * DTO для ответа на генерацию отчета.
 */
@Data
@Builder
public class ReportDto {
    private final long id;
    private final String numberPhone;
    private final String tariffIndex;
    private final List<OperationDto> payload;
    private final double totalCost;
    private final String monetaryUnit;

    @JsonCreator
    public ReportDto(long id, String numberPhone, String tariffIndex,
                     @JsonProperty("numbers") List<OperationDto> payload, double totalCost, String monetaryUnit) {
        this.id = id;
        this.numberPhone = numberPhone;
        this.tariffIndex = tariffIndex;
        this.payload = payload;
        this.totalCost = totalCost;
        this.monetaryUnit = monetaryUnit;
    }
}
