package com.acme.takemycar.rent.domain.services;

import com.acme.takemycar.rent.domain.model.aggregates.Rent;
import com.acme.takemycar.rent.domain.model.commands.CreateRentCommand;
import com.acme.takemycar.rent.domain.model.commands.DeleteRentCommand;
import com.acme.takemycar.rent.domain.model.commands.UpdateRentCommand;

import java.util.Optional;

public interface RentCommandService {
    Long handle(CreateRentCommand command);
    Optional<Rent> handle(UpdateRentCommand command);
    void handle(DeleteRentCommand command);
}
