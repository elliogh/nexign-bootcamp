package ru.ellio.brtservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ellio.brtservice.model.Tariff;

import java.util.Optional;

public interface TariffRepository extends JpaRepository<Tariff, Long> {
    Optional<Tariff> findAllByTariffId(String tariffId);
}
