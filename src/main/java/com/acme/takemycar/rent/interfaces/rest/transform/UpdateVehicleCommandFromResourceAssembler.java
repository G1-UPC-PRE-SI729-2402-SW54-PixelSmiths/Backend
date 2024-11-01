package com.acme.takemycar.rent.interfaces.rest.transform;

import com.acme.takemycar.rent.domain.model.commands.UpdateVehicleCommand;
import com.acme.takemycar.rent.interfaces.rest.resources.UpdateVehiclesResource;

public class UpdateVehicleCommandFromResourceAssembler {
    public static UpdateVehicleCommand toCommandFromResource(Long id, UpdateVehiclesResource resource) {
        return new UpdateVehicleCommand(id, resource.plate(),resource.model(),resource.year(),resource.price(),resource.image_Url());
    }
}
