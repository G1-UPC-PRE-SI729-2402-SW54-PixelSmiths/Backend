package com.acme.takemycar.rent.interfaces.rest.transform;

import com.acme.takemycar.rent.domain.model.commands.UpdateRentCommand;
import com.acme.takemycar.rent.interfaces.rest.resources.UpdateRentResource;

public class UpdateRentCommandFromResourceAssembler {
    public static UpdateRentCommand toCommandFromResource(Long id, UpdateRentResource resource) {
        return new UpdateRentCommand(id, resource.status(), resource.invoiceId(), resource.customerId(), resource.vehicleId());
    }
}
