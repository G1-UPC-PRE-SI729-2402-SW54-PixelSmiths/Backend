package com.acme.takemycar.rent.domain.services;

import com.acme.takemycar.rent.domain.model.aggregates.Vehicle;
import com.acme.takemycar.rent.domain.model.queries.GetAllVehiclesQuery;
import com.acme.takemycar.rent.domain.model.queries.GetVehicleByIdQuery;

import java.util.List;
import java.util.Optional;

public interface VehicleQueryService {
    Optional<Vehicle> handle(GetVehicleByIdQuery query);
    List<Vehicle> handle(GetAllVehiclesQuery query);
}
