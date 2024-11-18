package com.acme.takemycar.User.Interfaces.rest.Transform;

import com.acme.takemycar.User.Domain.Model.Commands.UpdateTenantCommand;
import com.acme.takemycar.User.Interfaces.rest.Resources.UpdateTenantResource;

public class UpdateTenantCommandFromResourceAssembler {
    public static UpdateTenantCommand toCommandFromResource(UpdateTenantResource resource, Long id) {
        return new UpdateTenantCommand(
                id,
                resource.name(),
                resource.address(),
                resource.phone(),
                resource.email(),
                resource.image_url()
        );
    }
}
