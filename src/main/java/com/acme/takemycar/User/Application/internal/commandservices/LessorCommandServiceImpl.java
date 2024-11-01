package com.acme.takemycar.User.Application.internal.commandservices;

import com.acme.takemycar.User.Domain.Model.Aggregates.Lessor;
import com.acme.takemycar.User.Domain.Model.Commands.CreateLessorCommand;
import com.acme.takemycar.User.Domain.Model.Commands.DeleteLessorCommand;
import com.acme.takemycar.User.Domain.Model.Commands.UpdateLessorCommand;
import com.acme.takemycar.User.Domain.Services.LessorCommandService;
import com.acme.takemycar.User.Infrastructure.persistence.jpa.repositories.LessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessorCommandServiceImpl implements LessorCommandService {

    private final LessorRepository lessorRepository;

    @Autowired
    public LessorCommandServiceImpl(LessorRepository lessorRepository) {
        this.lessorRepository = lessorRepository;
    }

    @Override
    public Long handle(CreateLessorCommand command) {
        Lessor lessor = new Lessor(command);
        try {
            lessorRepository.save(lessor);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving customer: " + e.getMessage());
        }
        return lessor.getId();
    }

    @Override
    public Optional<Lessor> handle(UpdateLessorCommand command) {
        var result = lessorRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Lessor does not exist");
        var lessorToUpdate = result.get();

        try {
            var updatedLessor = lessorRepository.save(lessorToUpdate.update(
                    command.name(),
                    command.address(),
                    command.phone(),
                    command.email(),
                    command.imageUrl()
            ));
            return Optional.of(updatedLessor);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating lessor: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteLessorCommand command) {
        if (!lessorRepository.existsById(command.lessorId())) {
            throw new IllegalArgumentException("Lessor does not exist");
        }
        try {
            lessorRepository.deleteById(command.lessorId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting lessor: " + e.getMessage());
        }
    }
}
