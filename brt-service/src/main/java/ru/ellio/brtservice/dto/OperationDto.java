package ru.ellio.brtservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * DTO для представления операции.
 */
@Data
@Builder
public class OperationDto {
    private final String callType;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Duration duration;
    private final double cost;
}
