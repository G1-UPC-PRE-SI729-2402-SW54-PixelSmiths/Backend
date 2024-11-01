package com.acme.takemycar.User.Interfaces.rest.Transform;

import com.acme.takemycar.User.Domain.Model.Commands.CreateLessorCommand;
import com.acme.takemycar.User.Interfaces.rest.Resources.CreateLessorResource;

public class CreateLessorCommandFromResourceAssembler {  public static CreateLessorCommand toCommandFromResource(CreateLessorResource resource) {
    return new CreateLessorCommand(
            resource.name(),
            resource.address(),
            resource.phone(),
            resource.email(),
            resource.image_url()
    );
}
}
