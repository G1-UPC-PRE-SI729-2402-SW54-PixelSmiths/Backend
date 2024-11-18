package com.acme.takemycar.User.Interfaces.rest;

import com.acme.takemycar.User.Domain.Model.Commands.DeleteLessorCommand;
import com.acme.takemycar.User.Domain.Model.Commands.DeleteTenantCommand;
import com.acme.takemycar.User.Domain.Model.Queries.GetAllTenantQuery;
import com.acme.takemycar.User.Domain.Model.Queries.GetTenantByIdQuery;
import com.acme.takemycar.User.Domain.Services.TenantCommandService;
import com.acme.takemycar.User.Domain.Services.TenantQueryService;
import com.acme.takemycar.User.Interfaces.rest.Resources.*;
import com.acme.takemycar.User.Interfaces.rest.Transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tenants", produces = "application/json")
@Tag(name = "Tenant", description = "Tenant Management Endpoints")
public class TenantController {
    private final TenantCommandService tenantCommandService;
    private final TenantQueryService tenantQueryService;

    public TenantController(TenantCommandService tenantCommandService, TenantQueryService tenantQueryService) {
        this.tenantCommandService = tenantCommandService;
        this.tenantQueryService = tenantQueryService;
    }

    @PostMapping
    public ResponseEntity<TenantResource> createCustomer(@RequestBody CreateTenantResource createTenantResource) {
        var createTenantCommand = CreateTenantCommandFromResourceAssembler.toCommandFromResource(createTenantResource);
        var tenantId = tenantCommandService.handle(createTenantCommand);
        if (tenantId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getTenantByIdQuery = new GetTenantByIdQuery(tenantId);
        var tenant = tenantQueryService.handle(getTenantByIdQuery);
        if (tenant.isEmpty()) return ResponseEntity.badRequest().build();
        var tenantResource = TenantResourceFromEntityAssembler.toResourceFromEntity(tenant.get());
        return new ResponseEntity<>(tenantResource, HttpStatus.CREATED);
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<TenantResource> getTenantById(@PathVariable Long tenantId) {
        var getTenantByIdQuery = new GetTenantByIdQuery(tenantId);
        var tenant = tenantQueryService.handle(getTenantByIdQuery);
        if (tenant.isEmpty()) return ResponseEntity.badRequest().build();
        var tenantResource = TenantResourceFromEntityAssembler.toResourceFromEntity(tenant.get());
        return ResponseEntity.ok(tenantResource);
    }

    @GetMapping
    public ResponseEntity<List<TenantResource>> getAllCustomers() {
        var getAllTenantQuery = new GetAllTenantQuery();
        var tenants = tenantQueryService.handle(getAllTenantQuery);
        var tenantResources = tenants.stream().map(TenantResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(tenantResources);
    }

    @PutMapping("/{tenantId}")
    public ResponseEntity<TenantResource> updateTenant(@PathVariable Long tenantId, @RequestBody UpdateTenantResource updateTenantResource) {
        var updateTenantCommand = UpdateTenantCommandFromResourceAssembler.toCommandFromResource(updateTenantResource, tenantId);
        var updatedTenant = tenantCommandService.handle(updateTenantCommand);
        if (updatedTenant.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var tenantResource = TenantResourceFromEntityAssembler.toResourceFromEntity(updatedTenant.get());
        return ResponseEntity.ok(tenantResource);
    }

    @DeleteMapping("/{tenantId}")
    public ResponseEntity<?> deleteTenant(@PathVariable Long tenantId) {
        var deleteTenantCommand = new DeleteTenantCommand(tenantId);
        tenantCommandService.handle(deleteTenantCommand);
        return ResponseEntity.ok("Tenant with given id successfully deleted");
    }
}
