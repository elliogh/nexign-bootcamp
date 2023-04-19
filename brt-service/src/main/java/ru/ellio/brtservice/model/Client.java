package ru.ellio.brtservice.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String numberPhone;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    private long balance;

}
