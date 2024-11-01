package com.acme.takemycar.rent.interfaces.rest.transform;

import com.acme.takemycar.rent.domain.model.commands.CreateVehicleCommand;
import com.acme.takemycar.rent.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {
    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource) {
        return new CreateVehicleCommand(
                resource.plate(),
                resource.model(),
                resource.year(),
                resource.price(),
                resource.image_Url(),
                resource.customer_i()
        );
    }
}