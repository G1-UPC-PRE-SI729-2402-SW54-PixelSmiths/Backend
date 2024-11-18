package com.acme.takemycar.iam.domain.model.commands;

public record SignUpCommand(String email, String password, String rol, String name) {
}
