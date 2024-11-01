package com.acme.takemycar.User.Infrastructure.persistence.jpa.repositories;

import com.acme.takemycar.User.Domain.Model.Aggregates.Lessor;
import com.acme.takemycar.rent.domain.model.aggregates.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessorRepository extends JpaRepository<Lessor, Long> {
    Optional<Lessor> findById(Long id);
}