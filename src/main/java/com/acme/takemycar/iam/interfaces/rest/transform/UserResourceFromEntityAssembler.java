package com.acme.takemycar.iam.interfaces.rest.transform;

import com.acme.takemycar.iam.domain.model.aggregates.User;
import com.acme.takemycar.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getId(), user.getEmail(), user.getRol(), user.getName());
    }
}
