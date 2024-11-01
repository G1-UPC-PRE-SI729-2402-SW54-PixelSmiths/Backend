package com.acme.takemycar.rent.application.internal.queryservices;

import com.acme.takemycar.rent.domain.model.aggregates.Vehicle;
import com.acme.takemycar.rent.domain.model.queries.GetAllVehiclesQuery;
import com.acme.takemycar.rent.domain.model.queries.GetVehicleByIdQuery;
import com.acme.takemycar.rent.domain.services.VehicleQueryService;
import com.acme.takemycar.rent.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleQueryServiceImpl implements VehicleQueryService {
    private final VehicleRepository vehicleRepository;

    public VehicleQueryServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return vehicleRepository.findById(query.id());
    }
    @Override
    public List<Vehicle> handle(GetAllVehiclesQuery query) {
        return vehicleRepository.findAll();
    }


}