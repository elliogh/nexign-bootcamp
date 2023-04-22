package ru.ellio.brtservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReportDto {
    private final long id;
    private final String numberPhone;
    private final String tariffIndex;
    private final List<OperationDto> payload;
    private final double totalCost;
    private final String monetaryUnit;
}
