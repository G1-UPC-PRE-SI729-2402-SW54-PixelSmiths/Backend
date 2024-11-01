package com.acme.takemycar.rent.application.internal.queryservices;

import com.acme.takemycar.rent.domain.model.aggregates.Invoice;
import com.acme.takemycar.rent.domain.model.queries.GetAllInvoicesQuery;
import com.acme.takemycar.rent.domain.model.queries.GetInvoiceByIdQuery;
import com.acme.takemycar.rent.domain.services.InvoiceQueryService;
import com.acme.takemycar.rent.infrastructure.persistence.jpa.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceQueryServiceImpl implements InvoiceQueryService {
    private final InvoiceRepository invoiceRepository;
    public InvoiceQueryServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Optional<Invoice> handle(GetInvoiceByIdQuery query) {
        return invoiceRepository.findById(query.id());
    }
    @Override
    public List<Invoice> handle(GetAllInvoicesQuery query) {
        return invoiceRepository.findAll();
    }

}
