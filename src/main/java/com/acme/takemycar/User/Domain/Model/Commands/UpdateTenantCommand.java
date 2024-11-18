package com.acme.takemycar.User.Domain.Model.Commands;

public record UpdateTenantCommand(Long id, String name, String address, String phone, String email, String imageUrl) {
}
