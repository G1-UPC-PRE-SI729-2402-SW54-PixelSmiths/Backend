package com.acme.takemycar.rent.interfaces.rest.transform;

import com.acme.takemycar.rent.domain.model.aggregates.Vehicle;
import com.acme.takemycar.rent.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
        return new VehicleResource(
                entity.getId(),
                entity.getPlate(),
                entity.getModel(),
                entity.getYear(),
                entity.getPrice(),
                entity.getImage_url(),
                entity.getLessor_id().getId()
        );
    }
}