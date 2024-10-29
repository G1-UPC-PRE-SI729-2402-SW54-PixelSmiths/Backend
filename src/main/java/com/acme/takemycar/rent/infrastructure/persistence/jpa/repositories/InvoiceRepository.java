package com.acme.takemycar.rent.infrastructure.persistence.jpa.repositories;

import com.acme.takemycar.rent.domain.model.aggregates.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository  extends JpaRepository<Invoice,Long> {

}
