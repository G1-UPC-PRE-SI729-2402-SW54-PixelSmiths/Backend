package com.acme.takemycar.User.Interfaces.rest.Transform;

import com.acme.takemycar.User.Domain.Model.Commands.CreateTenantCommand;
import com.acme.takemycar.User.Interfaces.rest.Resources.CreateTenantResource;

public class CreateTenantCommandFromResourceAssembler {  public static CreateTenantCommand toCommandFromResource(CreateTenantResource resource) {
    return new CreateTenantCommand(
            resource.name(),
            resource.address(),
            resource.phone(),
            resource.email(),
            resource.image_url()
    );
}
}