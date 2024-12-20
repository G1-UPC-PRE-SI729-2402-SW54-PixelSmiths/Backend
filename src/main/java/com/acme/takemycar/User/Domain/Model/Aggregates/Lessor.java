package com.acme.takemycar.User.Domain.Model.Aggregates;

import com.acme.takemycar.User.Domain.Model.Commands.CreateLessorCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Lessor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name="image_Url")
    private String image_url;

    public Lessor() {
        this.name = "default";
        this.address = "default";
        this.phone = "default";
        this.email = "default";
        this.image_url = "default";
    }

    public Lessor(CreateLessorCommand command) {
        this();
        this.name = command.name();
        this.address = command.address();
        this.phone = command.phone();
        this.email = command.email();
        this.image_url = command.imageUrl();
    }

    public Lessor update(String name, String address, String phone, String email, String imageUrl) {
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.image_url = imageUrl;
        return this;
    }

}