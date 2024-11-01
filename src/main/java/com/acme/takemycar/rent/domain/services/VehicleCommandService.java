package com.acme.takemycar.rent.domain.services;

import com.acme.takemycar.rent.domain.model.aggregates.Vehicle;
import com.acme.takemycar.rent.domain.model.commands.CreateVehicleCommand;
import com.acme.takemycar.rent.domain.model.commands.DeleteVehicleCommand;
import com.acme.takemycar.rent.domain.model.commands.UpdateVehicleCommand;

import java.util.Optional;

public interface VehicleCommandService {

    Long handle(CreateVehicleCommand command);
    Optional<Vehicle> handle(UpdateVehicleCommand command);
    void handle(DeleteVehicleCommand command);
}