package com.acme.takemycar.rent.domain.services;

import com.acme.takemycar.rent.domain.model.aggregates.Invoice;
import com.acme.takemycar.rent.domain.model.commands.CreateInvoiceCommand;
import com.acme.takemycar.rent.domain.model.commands.DeleteInvoiceCommand;
import com.acme.takemycar.rent.domain.model.commands.UpdateInvoiceCommand;

import java.util.Optional;

public interface InvoiceCommandService {
    Long handle(CreateInvoiceCommand command);
    Optional<Invoice> handle(UpdateInvoiceCommand command);
    void handle(DeleteInvoiceCommand command);
}
