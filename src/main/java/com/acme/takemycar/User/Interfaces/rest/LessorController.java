package com.acme.takemycar.User.Interfaces.rest;

import com.acme.takemycar.User.Domain.Model.Commands.DeleteLessorCommand;
import com.acme.takemycar.User.Domain.Model.Queries.GetAllLessorQuery;
import com.acme.takemycar.User.Domain.Model.Queries.GetLessorByIdQuery;
import com.acme.takemycar.User.Domain.Services.LessorCommandService;
import com.acme.takemycar.User.Domain.Services.LessorQueryService;
import com.acme.takemycar.User.Interfaces.rest.Resources.CreateLessorResource;
import com.acme.takemycar.User.Interfaces.rest.Resources.LessorResource;
import com.acme.takemycar.User.Interfaces.rest.Resources.UpdateLessorResource;
import com.acme.takemycar.User.Interfaces.rest.Transform.CreateLessorCommandFromResourceAssembler;
import com.acme.takemycar.User.Interfaces.rest.Transform.LessorResourceFromEntityAssembler;
import com.acme.takemycar.User.Interfaces.rest.Transform.UpdateLessorCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/lessors", produces = "application/json")
@Tag(name = "Lessor", description = "Lessor Management Endpoints")
public class LessorController {
    private final LessorCommandService lessorCommandService;
    private final LessorQueryService lessorQueryService;

    public LessorController(LessorCommandService lessorCommandService, LessorQueryService lessorQueryService) {
        this.lessorCommandService = lessorCommandService;
        this.lessorQueryService = lessorQueryService;
    }

    @PostMapping
    public ResponseEntity<LessorResource> createCustomer(@RequestBody CreateLessorResource createLessorResource) {
        var createLessorCommand = CreateLessorCommandFromResourceAssembler.toCommandFromResource(createLessorResource);
        var lessorId = lessorCommandService.handle(createLessorCommand);
        if (lessorId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getLessorByIdQuery = new GetLessorByIdQuery(lessorId);
        var lessor = lessorQueryService.handle(getLessorByIdQuery);
        if (lessor.isEmpty()) return ResponseEntity.badRequest().build();
        var lessorResource = LessorResourceFromEntityAssembler.toResourceFromEntity(lessor.get());
        return new ResponseEntity<>(lessorResource, HttpStatus.CREATED);
    }

    @GetMapping("/{lessorId}")
    public ResponseEntity<LessorResource> getCustomerById(@PathVariable Long lessorId) {
        var getLessorByIdQuery = new GetLessorByIdQuery(lessorId);
        var lessor = lessorQueryService.handle(getLessorByIdQuery);
        if (lessor.isEmpty()) return ResponseEntity.badRequest().build();
        var lessorResource = LessorResourceFromEntityAssembler.toResourceFromEntity(lessor.get());
        return ResponseEntity.ok(lessorResource);
    }

    @GetMapping
    public ResponseEntity<List<LessorResource>> getAllCustomers() {
        var getAllLessorsQuery = new GetAllLessorQuery();
        var lessors = lessorQueryService.handle(getAllLessorsQuery);
        var lessorResources = lessors.stream().map(LessorResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(lessorResources);
    }

    @PutMapping("/{lessorId}")
    public ResponseEntity<LessorResource> updateLessor(@PathVariable Long lessorId, @RequestBody UpdateLessorResource updateLessorResource) {
        var updateLessorCommand = UpdateLessorCommandFromResourceAssembler.toCommandFromResource(updateLessorResource, lessorId);
        var updatedLessor = lessorCommandService.handle(updateLessorCommand);
        if (updatedLessor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var lessorResource = LessorResourceFromEntityAssembler.toResourceFromEntity(updatedLessor.get());
        return ResponseEntity.ok(lessorResource);
    }

    @DeleteMapping("/{lessorId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long lessorId) {
        var deleteLessorCommand = new DeleteLessorCommand(lessorId);
        lessorCommandService.handle(deleteLessorCommand);
        return ResponseEntity.ok("Lessor with given id successfully deleted");
    }
}
