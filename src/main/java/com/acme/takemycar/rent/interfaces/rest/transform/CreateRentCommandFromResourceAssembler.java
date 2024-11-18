package com.acme.takemycar.rent.interfaces.rest.transform;

import com.acme.takemycar.rent.domain.model.commands.CreateRentCommand;
import com.acme.takemycar.rent.interfaces.rest.resources.CreateRentResource;

public class CreateRentCommandFromResourceAssembler {
    public static CreateRentCommand toCommandFromResource(CreateRentResource resource) {
        return new CreateRentCommand(resource.status(), resource.invoiceId(), resource.tenantId(), resource.vehicleId());
    }
}
