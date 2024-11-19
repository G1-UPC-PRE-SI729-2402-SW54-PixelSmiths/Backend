package com.acme.takemycar.iam.interfaces.rest.transform;

import com.acme.takemycar.iam.domain.model.aggregates.User;
import com.acme.takemycar.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getEmail(), token);
    }
}
