package com.acme.takemycar.rent.interfaces.rest;

import com.acme.takemycar.rent.domain.model.commands.DeleteVehicleCommand;
import com.acme.takemycar.rent.domain.model.queries.GetAllVehiclesQuery;
import com.acme.takemycar.rent.domain.model.queries.GetVehicleByIdQuery;
import com.acme.takemycar.rent.domain.services.VehicleCommandService;
import com.acme.takemycar.rent.domain.services.VehicleQueryService;
import com.acme.takemycar.rent.interfaces.rest.resources.CreateVehicleResource;
import com.acme.takemycar.rent.interfaces.rest.resources.UpdateVehiclesResource;
import com.acme.takemycar.rent.interfaces.rest.resources.VehicleResource;
import com.acme.takemycar.rent.interfaces.rest.transform.CreateVehicleCommandFromResourceAssembler;
import com.acme.takemycar.rent.interfaces.rest.transform.UpdateVehicleCommandFromResourceAssembler;
import com.acme.takemycar.rent.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/vehicles", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Vehicle", description = "Vehicle Management Endpoints")
public class VehicleController {
    public VehicleController(VehicleQueryService vehicleQueryService, VehicleCommandService vehicleCommandService) {
        this.vehicleQueryService = vehicleQueryService;
        this.vehicleCommandService = vehicleCommandService;
    }

    private final VehicleQueryService vehicleQueryService;
    private final VehicleCommandService vehicleCommandService;

    @PostMapping
    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource createVehicleResource) {
        var createVehicleCommand = CreateVehicleCommandFromResourceAssembler.toCommandFromResource(createVehicleResource);
        var vehicleId = vehicleCommandService.handle(createVehicleCommand);
        if (vehicleId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
        var vehicle = vehicleQueryService.handle(getVehicleByIdQuery);
        if (vehicle.isEmpty()) return ResponseEntity.badRequest().build();
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return new ResponseEntity<>(vehicleResource, HttpStatus.CREATED);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResource> getVehicleById(@PathVariable Long vehicleId) {
        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
        var vehicle = vehicleQueryService.handle(getVehicleByIdQuery);
        if (vehicle.isEmpty()) return ResponseEntity.badRequest().build();
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle.get());
        return ResponseEntity.ok(vehicleResource);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResource>> getAllVehicles() {
        var getAllVehiclesQuery = new GetAllVehiclesQuery();
        var courses = vehicleQueryService.handle(getAllVehiclesQuery);
        var vehicleResources = courses.stream().map(VehicleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(vehicleResources);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleResource> updateVehicle(@PathVariable Long vehicleId, @RequestBody UpdateVehiclesResource updateVehicleResource) {
        var updateVehicleCommand = UpdateVehicleCommandFromResourceAssembler.toCommandFromResource(vehicleId, updateVehicleResource);
        var updatedVehicle = vehicleCommandService.handle(updateVehicleCommand);
        if (updatedVehicle.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(updatedVehicle.get());
        return ResponseEntity.ok(vehicleResource);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long vehicleId) {
        var deleteVehicleCommand = new DeleteVehicleCommand(vehicleId);
        vehicleCommandService.handle(deleteVehicleCommand);
        return ResponseEntity.ok("Vehicle with given id successfully deleted");
    }

}