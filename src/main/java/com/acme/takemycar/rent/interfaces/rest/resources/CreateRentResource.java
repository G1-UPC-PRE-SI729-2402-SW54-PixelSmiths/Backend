package com.acme.takemycar.rent.interfaces.rest.resources;

public record CreateRentResource(String status, Long invoiceId, Long tenantId, Long vehicleId) {
}
