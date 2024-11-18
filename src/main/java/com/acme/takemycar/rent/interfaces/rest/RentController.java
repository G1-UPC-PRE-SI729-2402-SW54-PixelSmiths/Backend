package com.acme.takemycar.rent.interfaces.rest;

import com.acme.takemycar.rent.domain.model.commands.DeleteRentCommand;
import com.acme.takemycar.rent.domain.model.queries.GetAllRentQuery;
import com.acme.takemycar.rent.domain.model.queries.GetRentByIdQuery;
import com.acme.takemycar.rent.domain.services.RentCommandService;
import com.acme.takemycar.rent.domain.services.RentQueryService;
import com.acme.takemycar.rent.interfaces.rest.resources.CreateRentResource;
import com.acme.takemycar.rent.interfaces.rest.resources.RentResource;
import com.acme.takemycar.rent.interfaces.rest.resources.UpdateRentResource;
import com.acme.takemycar.rent.interfaces.rest.transform.CreateRentCommandFromResourceAssembler;
import com.acme.takemycar.rent.interfaces.rest.transform.RentResourceFromEntityAssembler;
import com.acme.takemycar.rent.interfaces.rest.transform.UpdateRentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/rents", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Rents", description = "Rent Management Endpoints")
public class RentController {
    private final RentQueryService rentQueryService;
    private final RentCommandService rentCommandService;

    public RentController(RentQueryService rentQueryService, RentCommandService rentCommandService) {
        this.rentQueryService = rentQueryService;
        this.rentCommandService = rentCommandService;
    }
    @PostMapping
    public ResponseEntity<RentResource> createVehicle(@RequestBody CreateRentResource createRentResource) {
        var createRentCommand = CreateRentCommandFromResourceAssembler.toCommandFromResource(createRentResource);
        var rentId = rentCommandService.handle(createRentCommand);
        if (rentId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getRentByIdQuery = new GetRentByIdQuery(rentId);
        var rent = rentQueryService.handle(getRentByIdQuery);
        if (rent.isEmpty()) return ResponseEntity.badRequest().build();
        var rentResource = RentResourceFromEntityAssembler.toResourceFromEntity(rent.get());
        return new ResponseEntity<>(rentResource, HttpStatus.CREATED);
    }
    @GetMapping("/{rentId}")
    public ResponseEntity<RentResource> getRentById(@PathVariable Long rentId) {
        var getRentByIdQuery = new GetRentByIdQuery(rentId);
        var rent = rentQueryService.handle(getRentByIdQuery);
        if (rent.isEmpty()) return ResponseEntity.badRequest().build();
        var rentResource = RentResourceFromEntityAssembler.toResourceFromEntity(rent.get());
        return ResponseEntity.ok(rentResource);
    }
    @GetMapping
    public ResponseEntity<List<RentResource>> getAllRents() {
        var getAllRentsQuery = new GetAllRentQuery();
        var rents = rentQueryService.handle(getAllRentsQuery);
        var rentResources = rents.stream().map(RentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(rentResources);
    }

    @PutMapping("/{rentId}")
    public ResponseEntity<RentResource> updateRent(@PathVariable Long rentId, @RequestBody UpdateRentResource updateRentResource) {
        var updateRentCommand = UpdateRentCommandFromResourceAssembler.toCommandFromResource(rentId, updateRentResource);
        var updatedRent = rentCommandService.handle(updateRentCommand);
        if (updatedRent.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var rentResource = RentResourceFromEntityAssembler.toResourceFromEntity(updatedRent.get());
        return ResponseEntity.ok(rentResource);
    }


    /**
     * Deletes a rent.
     *
     * @param rentId the id of the rent to be deleted
     * @return Deletion confirmation message
     */
    @DeleteMapping("/{rentId}")
    public ResponseEntity<?> deleteRent(@PathVariable Long rentId) {
        var deleteRentCommand = new DeleteRentCommand(rentId);
        rentCommandService.handle(deleteRentCommand);
        return ResponseEntity.ok("Rent with given id successfully deleted");
    }
}
