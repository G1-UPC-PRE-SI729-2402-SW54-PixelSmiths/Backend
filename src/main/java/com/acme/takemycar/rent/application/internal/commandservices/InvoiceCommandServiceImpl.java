package com.acme.takemycar.rent.application.internal.commandservices;

import com.acme.takemycar.rent.domain.model.aggregates.Invoice;
import com.acme.takemycar.rent.domain.model.commands.CreateInvoiceCommand;
import com.acme.takemycar.rent.domain.model.commands.DeleteInvoiceCommand;
import com.acme.takemycar.rent.domain.model.commands.UpdateInvoiceCommand;
import com.acme.takemycar.rent.domain.services.InvoiceCommandService;
import com.acme.takemycar.rent.infrastructure.persistence.jpa.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceCommandServiceImpl implements InvoiceCommandService {
    private final InvoiceRepository invoiceRepository;
    public InvoiceCommandServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Long handle(CreateInvoiceCommand command) {
        var invoice = new Invoice(command);
        try {
            invoiceRepository.save(invoice);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving details: " + e.getMessage());
        }
        return invoice.getId();
    }

    @Override
    public Optional<Invoice> handle(UpdateInvoiceCommand command) {
        var result = invoiceRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Invoice does not exist");
        var invoiceToUpdate = result.get();
        try {
            var invoiceUpdated = invoiceRepository.save(invoiceToUpdate.updateInvoice(command.number(),command.issue_date(),command.total(),command.status(),command.detail()));
            return Optional.of(invoiceUpdated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating invoice: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteInvoiceCommand command) {
        if (!invoiceRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Invoice does not exist");
        }
        try {
            invoiceRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting detail: " + e.getMessage());
        }

    }


}
