package com.acme.takemycar.rent.application.internal.queryservices;

import com.acme.takemycar.rent.domain.model.aggregates.Rent;
import com.acme.takemycar.rent.domain.model.queries.GetAllRentQuery;
import com.acme.takemycar.rent.domain.model.queries.GetRentByIdQuery;
import com.acme.takemycar.rent.domain.services.RentQueryService;
import com.acme.takemycar.rent.infrastructure.persistence.jpa.repositories.RentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RentQueryServiceImpl implements RentQueryService {
    private final RentRepository rentRepository;
    public RentQueryServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }
    @Override
    public Optional<Rent> handle(GetRentByIdQuery query) {
        return rentRepository.findById(query.id());
    }
    @Override
    public List<Rent> handle(GetAllRentQuery query) {
        return rentRepository.findAll();
    }
}
