package com.acme.takemycar.rent.domain.model.commands;

public record UpdateRentCommand(Long id, String status, Long invoiceId, Long tenantId, Long vehicleId) {
}
