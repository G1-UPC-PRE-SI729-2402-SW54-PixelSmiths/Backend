package com.acme.takemycar.User.Infrastructure.persistence.jpa.repositories;

import com.acme.takemycar.User.Domain.Model.Aggregates.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Optional<Tenant> findById(Long id);
}
