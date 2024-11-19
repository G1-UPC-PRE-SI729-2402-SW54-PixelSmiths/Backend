package com.acme.takemycar.iam.domain.model.aggregates;

import com.acme.takemycar.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * User aggregate root
 * This class represents the aggregate root for the User entity.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @NotNull
    private String rol;

    @NotBlank
    @NotNull
    private String name;

    private String address;

    private String phone;

    private String image_url;

    public User() {

    }
    public User(String email, String password,String rol, String name) {
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.name = name;
        this.address = Strings.EMPTY;
        this.phone = Strings.EMPTY;
        this.image_url = Strings.EMPTY;
    }



}
