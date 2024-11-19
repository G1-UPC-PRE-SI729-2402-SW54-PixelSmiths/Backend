package com.acme.takemycar.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String email, String token) {

}
