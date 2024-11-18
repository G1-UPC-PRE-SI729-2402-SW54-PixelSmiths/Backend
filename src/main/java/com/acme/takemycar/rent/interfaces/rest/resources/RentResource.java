package com.acme.takemycar.rent.interfaces.rest.resources;

public record RentResource(Long id, String status, Long invoiceId, Long customerId, Long vehicleId) {
}
