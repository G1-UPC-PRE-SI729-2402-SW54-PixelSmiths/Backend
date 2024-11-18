package com.acme.takemycar.User.Domain.Model.Commands;

public record CreateTenantCommand(String name, String address, String phone, String email, String imageUrl) {
}
