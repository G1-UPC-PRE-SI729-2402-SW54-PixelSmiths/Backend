package com.acme.takemycar.rent.interfaces.rest.resources;

public record VehicleResource(Long id, String plate, String model, Long year, String price, String image_Url, Long customer_id) {
}
