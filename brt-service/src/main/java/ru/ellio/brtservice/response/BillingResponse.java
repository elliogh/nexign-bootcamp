package ru.ellio.brtservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Data
@AllArgsConstructor
public class BillingResponse {
    private final String numberPhone;
    private final String callType;
    private final String tariffId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Duration duration;
    private final double cost;
}
