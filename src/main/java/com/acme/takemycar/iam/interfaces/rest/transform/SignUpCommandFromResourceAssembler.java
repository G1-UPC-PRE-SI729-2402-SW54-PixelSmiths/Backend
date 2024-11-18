package com.acme.takemycar.iam.interfaces.rest.transform;

import com.acme.takemycar.iam.domain.model.commands.SignUpCommand;
import com.acme.takemycar.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.email(), resource.password(), resource.rol(), resource.name());
    }
}
