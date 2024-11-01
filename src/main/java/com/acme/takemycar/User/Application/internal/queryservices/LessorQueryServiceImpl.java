package com.acme.takemycar.User.Application.internal.queryservices;

import com.acme.takemycar.User.Domain.Model.Aggregates.Lessor;
import com.acme.takemycar.User.Domain.Model.Queries.GetAllLessorQuery;
import com.acme.takemycar.User.Domain.Model.Queries.GetLessorByIdQuery;
import com.acme.takemycar.User.Domain.Services.LessorQueryService;
import com.acme.takemycar.User.Infrastructure.persistence.jpa.repositories.LessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessorQueryServiceImpl implements LessorQueryService {

    private final LessorRepository lessorRepository;

    public LessorQueryServiceImpl(LessorRepository lessorRepository) {
        this.lessorRepository = lessorRepository;
    }

    @Override
    public Optional<Lessor> handle(GetLessorByIdQuery query) {
        return lessorRepository.findById(query.lessorId());
    }

    @Override
    public List<Lessor> handle(GetAllLessorQuery query) {
        return lessorRepository.findAll();
    }
}