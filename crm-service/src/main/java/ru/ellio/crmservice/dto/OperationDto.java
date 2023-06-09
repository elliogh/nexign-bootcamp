package ru.ellio.crmservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import ru.ellio.crmservice.json.DurationSerializer;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * DTO для представления операции.
 */
@Data
@Builder
public class OperationDto {
    private final String callType;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime endTime;
    @JsonSerialize(using = DurationSerializer.class)
    private final Duration duration;
    private final double cost;
}
