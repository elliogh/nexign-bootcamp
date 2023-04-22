package ru.ellio.hrsservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Ответ от HRS c посчитанной стоимостью звонка.
 */
@Getter
@AllArgsConstructor
public class BillingResponse {
    private final String numberPhone;
    private final String callType;
    private final String tariffId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final long duration;
    private final double cost;
}
