package ru.ellio.brtservice.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
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

    private double balance;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Operation> payload = new java.util.ArrayList<>();

    private double totalCost;

    private String monetaryUnit;
}
