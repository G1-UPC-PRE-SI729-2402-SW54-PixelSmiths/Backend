package com.acme.takemycar.rent.domain.model.commands;

public record UpdateVehicleCommand(Long id, String plate, String model, Long year, String price, String image_Url) {
}
