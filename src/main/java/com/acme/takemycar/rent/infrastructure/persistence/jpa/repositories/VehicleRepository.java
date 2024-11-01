package com.acme.takemycar.rent.infrastructure.persistence.jpa.repositories;

import com.acme.takemycar.rent.domain.model.aggregates.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findById(Long id);
}