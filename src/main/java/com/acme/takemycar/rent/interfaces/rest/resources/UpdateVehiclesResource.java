package com.acme.takemycar.rent.interfaces.rest.resources;

public record UpdateVehiclesResource(String plate, String model, Long year, String price, String image_Url) {
}
