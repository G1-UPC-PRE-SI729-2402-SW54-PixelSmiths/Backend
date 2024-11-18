package com.acme.takemycar.rent.domain.services;

import com.acme.takemycar.rent.domain.model.aggregates.Rent;
import com.acme.takemycar.rent.domain.model.queries.GetAllRentQuery;
import com.acme.takemycar.rent.domain.model.queries.GetRentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RentQueryService {
    Optional<Rent> handle(GetRentByIdQuery query);
    List<Rent> handle(GetAllRentQuery query);
}
