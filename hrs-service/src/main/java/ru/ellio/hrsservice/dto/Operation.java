package ru.ellio.hrsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Operation {
    private String callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String tariffId;
    private double price;
    private double defaultMinutePrice;
    private double tariffMinutePrice;
    private int minutesLeft;
    private boolean incomingFree;
}
