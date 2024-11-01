package com.acme.takemycar.rent.application.internal.commandservices;

import com.acme.takemycar.User.Infrastructure.persistence.jpa.repositories.LessorRepository;
import com.acme.takemycar.rent.domain.model.aggregates.Vehicle;
import com.acme.takemycar.rent.domain.model.commands.CreateVehicleCommand;
import com.acme.takemycar.rent.domain.model.commands.DeleteVehicleCommand;
import com.acme.takemycar.rent.domain.model.commands.UpdateVehicleCommand;
import com.acme.takemycar.rent.domain.services.VehicleCommandService;
import com.acme.takemycar.rent.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleCommandServiceImpl implements VehicleCommandService {
    private final VehicleRepository vehicleRepository;
    private final LessorRepository lessorRepository;

    @Autowired
    public VehicleCommandServiceImpl(VehicleRepository vehicleRepository, LessorRepository lessorRepository) {
        this.vehicleRepository = vehicleRepository;
        this.lessorRepository = lessorRepository;
    }

    @Override
    public Long handle(CreateVehicleCommand command) {
        var lessorResult = lessorRepository.findById(command.customerId());
        if (lessorResult.isEmpty()) throw new IllegalArgumentException("Lessor does not exist");
        var lessor = lessorResult.get();

        var vehicle = new Vehicle(command, lessor);
        try {
            vehicleRepository.save(vehicle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving vehicle: " + e.getMessage());
        }
        return vehicle.getId();
    }

    @Override
    public Optional<Vehicle> handle(UpdateVehicleCommand command) {
        var result = vehicleRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Vehicle does not exist");
        var vehicleToUpdate = result.get();


        try {
            var updatedVehicle = vehicleRepository.save(vehicleToUpdate.updateVehicle(
                    command.plate(),
                    command.model(),
                    command.year(),
                    command.price(),
                    command.image_Url()
            ));
            return Optional.of(updatedVehicle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating vehicle: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteVehicleCommand command) {
        if (!vehicleRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Vehicle does not exist");
        }
        try {
            vehicleRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting vehicle: " + e.getMessage());
        }
    }
}
