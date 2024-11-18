package com.acme.takemycar.rent.application.internal.commandservices;

import com.acme.takemycar.User.Infrastructure.persistence.jpa.repositories.TenantRepository;
import com.acme.takemycar.rent.domain.model.aggregates.Rent;
import com.acme.takemycar.rent.domain.model.commands.CreateRentCommand;
import com.acme.takemycar.rent.domain.model.commands.DeleteRentCommand;
import com.acme.takemycar.rent.domain.model.commands.UpdateRentCommand;
import com.acme.takemycar.rent.domain.services.RentCommandService;
import com.acme.takemycar.rent.infrastructure.persistence.jpa.repositories.InvoiceRepository;
import com.acme.takemycar.rent.infrastructure.persistence.jpa.repositories.RentRepository;
import com.acme.takemycar.rent.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentCommandServiceImpl implements RentCommandService {

    public RentCommandServiceImpl(RentRepository rentRepository, InvoiceRepository invoiceRepository, TenantRepository tenantRepository, VehicleRepository vehicleRepository) {
        this.rentRepository = rentRepository;
        this.invoiceRepository = invoiceRepository;
        this.tenantRepository = tenantRepository;
        this.vehicleRepository = vehicleRepository;
    }

    private final RentRepository rentRepository;
    private final InvoiceRepository invoiceRepository;
    private final TenantRepository tenantRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public Long handle(CreateRentCommand command) {

        var invoiceResult = invoiceRepository.findById(command.invoiceId());
        var invoice = invoiceResult.get();

        var customerResult = tenantRepository.findById(command.tenantId());
        var customer = customerResult.get();

        var vehicleResult = vehicleRepository.findById(command.vehicleId());
        var vehicle = vehicleResult.get();

        var rent = new Rent(command, invoice, customer, vehicle);
        try {
            rentRepository.save(rent);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving rent: " + e.getMessage());
        }
        return rent.getId();
    }

    @Override
    public Optional<Rent> handle(UpdateRentCommand command) {
        var result = rentRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Rent does not exist");
        var rentToUpdate = result.get();

        var invoiceResult = invoiceRepository.findById(command.id());
        if (invoiceResult.isEmpty()) throw new IllegalArgumentException("Invoice does not exist");
        var invoice = invoiceResult.get();

        var customerResult = tenantRepository.findById(command.id());
        if (customerResult.isEmpty()) throw new IllegalArgumentException("Tenant does not exist");
        var customer = customerResult.get();

        var vehicleResult = vehicleRepository.findById(command.id());
        if (vehicleResult.isEmpty()) throw new IllegalArgumentException("Vehicle does not exist");
        var vehicle = vehicleResult.get();
        try {
            var rentUpdated = rentRepository.save(rentToUpdate.updateRent(command.status(), invoice, customer, vehicle));
            return Optional.of(rentUpdated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating rent: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteRentCommand command) {
        if (!rentRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Rent does not exist");
        }
        try {
            rentRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting rent " + e.getMessage());
        }

    }
}
