package com.acme.takemycar.iam.domain.model.valueobjects;

public enum Roles {
    ROLE_OWNER,
    ROLE_LESSOR;
    public static boolean exists(String rol) {
        for (Roles role : Roles.values()) {
            if (role.name().equals(rol)) {
                return true;
            }
        }
        return false;
    }
}
