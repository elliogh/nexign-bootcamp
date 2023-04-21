package ru.ellio.brtservice.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tariff")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String tariffId;

    private String name;

    private double price;

    private double defaultMinutePrice;

    private double tariffMinutePrice;

    private int minutesLeft;

    private boolean incomingFree;

}
