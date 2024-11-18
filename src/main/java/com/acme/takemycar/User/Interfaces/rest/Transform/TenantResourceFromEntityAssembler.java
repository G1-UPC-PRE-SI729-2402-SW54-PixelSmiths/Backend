package com.acme.takemycar.User.Interfaces.rest.Transform;

import com.acme.takemycar.User.Domain.Model.Aggregates.Tenant;
import com.acme.takemycar.User.Interfaces.rest.Resources.TenantResource;

public class TenantResourceFromEntityAssembler {
    public static TenantResource toResourceFromEntity(Tenant entity) {
        return new TenantResource(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getImage_url()
        );
    }
}