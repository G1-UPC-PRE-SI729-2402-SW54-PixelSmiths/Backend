package com.acme.takemycar.User.Interfaces.rest.Transform;

import com.acme.takemycar.User.Domain.Model.Aggregates.Lessor;
import com.acme.takemycar.User.Interfaces.rest.Resources.LessorResource;

public class LessorResourceFromEntityAssembler {
    public static LessorResource toResourceFromEntity(Lessor entity) {
        return new LessorResource(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getImage_url()
        );
    }
}