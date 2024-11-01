package com.acme.takemycar.rent.domain.model.aggregates;

import com.acme.takemycar.User.Domain.Model.Aggregates.Lessor;
import com.acme.takemycar.rent.domain.model.commands.CreateVehicleCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.constraints.URL;

@Getter
@Entity
public class Vehicle {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "plate", nullable = false)
    private String plate;

    @Getter
    @Column(name = "model", nullable = false)
    private String model;

    @Getter
    @Column(name = "year", nullable = false)
    private Long year;

    @Getter
    @Column(name = "price", nullable = false)
    private String price;

    @Getter @URL
    @Column(name = "image_url", nullable = false)
    private String image_url;

    @Getter
    @ManyToOne
    @JoinColumn(name = "lessor_id")
    private Lessor lessor_id;

    public Vehicle() {
        this.plate = Strings.EMPTY;
        this.model = Strings.EMPTY;
        this.year = 0L;
        this.price = Strings.EMPTY;
        this.image_url = Strings.EMPTY;
    }

    public Vehicle(String plate, String make, String model, Long year, String price, String image_Url, Long mileages, Lessor lessor) {
        this();
        this.plate = plate;
        this.model = model;
        this.year = year;
        this.price = price;
        this.image_url = image_Url;
        this.lessor_id = lessor;
    }

    public Vehicle(CreateVehicleCommand command, Lessor lessor){
        this();
        this.plate = command.plate();
        this.model = command.model();
        this.year = command.year();
        this.price = command.price();
        this.image_url = command.image_Url();
        this.lessor_id = lessor;
    }

    public Vehicle updateVehicle(String plate, String model, Long year, String price, String image_Url) {
        this.plate = plate;
        this.model = model;
        this.year = year;
        this.price = price;
        this.image_url = image_Url;
        return this;
    }


}

