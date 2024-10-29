package com.acme.takemycar.rent.domain.services;

import com.acme.takemycar.rent.domain.model.aggregates.Invoice;
import com.acme.takemycar.rent.domain.model.queries.GetAllInvoicesQuery;
import com.acme.takemycar.rent.domain.model.queries.GetInvoiceByIdQuery;

import java.util.List;
import java.util.Optional;

public interface InvoiceQueryService {
    Optional<Invoice> handle(GetInvoiceByIdQuery query);
    List<Invoice> handle(GetAllInvoicesQuery query);
}

