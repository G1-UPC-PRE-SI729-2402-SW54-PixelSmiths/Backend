package com.acme.takemycar.rent.domain.model.aggregates;

import com.acme.takemycar.User.Domain.Model.Aggregates.Tenant;
import com.acme.takemycar.rent.domain.model.commands.CreateRentCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
public class Rent {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "status", nullable = false)
    private String status;

    @Getter
    @ManyToOne
    @JoinColumn(name = "invoiceId", nullable = true)
    private Invoice invoiceId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Tenant tenantId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicleId;


    public Rent(String status, Invoice invoiceId, Tenant tenantId, Vehicle vehicleId) {
        this();
        this.status = status;
        this.invoiceId = invoiceId;
        this.tenantId = tenantId;
        this.vehicleId = vehicleId;
    }

    public Rent() {
        this.status = Strings.EMPTY;
        this.invoiceId =  null;
        this.tenantId =  null ;
        this.vehicleId = null;
    }

    public Rent(CreateRentCommand command, Invoice invoiceId, Tenant tenantId, Vehicle vehicleId) {
        this();
        this.status = command.status();
        this.invoiceId = invoiceId;
        this.tenantId = tenantId;
        this.vehicleId = vehicleId;
    }
    public Rent updateRent(String status, Invoice invoiceId, Tenant tenantId, Vehicle vehicleId) {
        this.status = status;
        this.invoiceId = invoiceId;
        this.tenantId = tenantId;
        this.vehicleId = vehicleId;
        return this;
    }
}
