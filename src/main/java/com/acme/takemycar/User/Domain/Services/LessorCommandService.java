package com.acme.takemycar.User.Domain.Services;

import com.acme.takemycar.User.Domain.Model.Aggregates.Lessor;
import com.acme.takemycar.User.Domain.Model.Commands.CreateLessorCommand;
import com.acme.takemycar.User.Domain.Model.Commands.DeleteLessorCommand;
import com.acme.takemycar.User.Domain.Model.Commands.UpdateLessorCommand;

import java.util.Optional;

public interface LessorCommandService {
    Long handle(CreateLessorCommand command);
    Optional<Lessor> handle(UpdateLessorCommand command);
    void handle(DeleteLessorCommand command);
}
