package com.acme.takemycar.rent.infrastructure.persistence.jpa.repositories;

import com.acme.takemycar.rent.domain.model.aggregates.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

}
