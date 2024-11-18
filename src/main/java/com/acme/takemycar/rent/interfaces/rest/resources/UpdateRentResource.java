package com.acme.takemycar.rent.interfaces.rest.resources;

public record UpdateRentResource(String status, Long invoiceId, Long customerId, Long vehicleId) {
}
