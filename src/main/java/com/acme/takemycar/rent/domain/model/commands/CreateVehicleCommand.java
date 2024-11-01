package com.acme.takemycar.rent.domain.model.commands;

public record CreateVehicleCommand(String plate, String model, Long year, String price, String image_Url, Long customerId) {
}
