package com.acme.takemycar.User.Application.internal.commandservices;

import com.acme.takemycar.User.Domain.Model.Aggregates.Tenant;
import com.acme.takemycar.User.Domain.Model.Commands.CreateTenantCommand;
import com.acme.takemycar.User.Domain.Model.Commands.DeleteTenantCommand;
import com.acme.takemycar.User.Domain.Model.Commands.UpdateTenantCommand;
import com.acme.takemycar.User.Domain.Services.TenantCommandService;
import com.acme.takemycar.User.Infrastructure.persistence.jpa.repositories.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantCommandServiceImpl implements TenantCommandService {

    private final TenantRepository tenantRepository;

    @Autowired
    public TenantCommandServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Long handle(CreateTenantCommand command) {
        Tenant tenant = new Tenant(command);
        try {
            tenantRepository.save(tenant);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving tenant: " + e.getMessage());
        }
        return tenant.getId();
    }

    @Override
    public Optional<Tenant> handle(UpdateTenantCommand command) {
        var result = tenantRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Tenant does not exist");
        var tenant = result.get();

        try {
            var updatedTenant = tenantRepository.save(tenant.update(
                    command.name(),
                    command.address(),
                    command.phone(),
                    command.email(),
                    command.imageUrl()
            ));
            return Optional.of(updatedTenant);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating tenant: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteTenantCommand command) {
        if (!tenantRepository.existsById(command.tenantId())) {
            throw new IllegalArgumentException("Tenant does not exist");
        }
        try {
            tenantRepository.deleteById(command.tenantId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting tenant: " + e.getMessage());
        }
    }
}
