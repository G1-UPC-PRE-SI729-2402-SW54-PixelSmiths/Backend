package com.acme.takemycar.rent.interfaces.rest.transform;

import com.acme.takemycar.rent.domain.model.aggregates.Rent;
import com.acme.takemycar.rent.interfaces.rest.resources.RentResource;

public class RentResourceFromEntityAssembler {
    public static RentResource toResourceFromEntity(Rent entity) {
        return new RentResource(entity.getId(), entity.getStatus(), entity.getInvoiceId().getId(), entity.getTenantId().getId(), entity.getVehicleId().getId());
    }
}
