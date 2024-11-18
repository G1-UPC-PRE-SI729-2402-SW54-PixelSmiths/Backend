package com.acme.takemycar.User.Domain.Services;

import com.acme.takemycar.User.Domain.Model.Aggregates.Tenant;
import com.acme.takemycar.User.Domain.Model.Commands.*;

import java.util.Optional;

public interface TenantCommandService {
    Long handle(CreateTenantCommand command);
    Optional<Tenant> handle(UpdateTenantCommand command);
    void handle(DeleteTenantCommand command);
}
