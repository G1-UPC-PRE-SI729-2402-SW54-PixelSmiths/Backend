package com.acme.takemycar.User.Interfaces.rest.Transform;

import com.acme.takemycar.User.Domain.Model.Commands.UpdateLessorCommand;
import com.acme.takemycar.User.Interfaces.rest.Resources.UpdateLessorResource;

public class UpdateLessorCommandFromResourceAssembler {
    public static UpdateLessorCommand toCommandFromResource(UpdateLessorResource resource, Long id) {
        return new UpdateLessorCommand(
                id,
                resource.name(),
                resource.address(),
                resource.phone(),
                resource.email(),
                resource.image_url()
        );
    }
}
