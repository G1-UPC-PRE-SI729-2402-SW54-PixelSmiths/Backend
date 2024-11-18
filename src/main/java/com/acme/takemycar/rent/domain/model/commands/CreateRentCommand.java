package com.acme.takemycar.rent.domain.model.commands;

public record CreateRentCommand(String status, Long invoiceId, Long tenantId, Long vehicleId) {
}
