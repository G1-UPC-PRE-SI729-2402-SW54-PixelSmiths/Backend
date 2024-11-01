package com.acme.takemycar.User.Domain.Services;

import com.acme.takemycar.User.Domain.Model.Aggregates.Lessor;
import com.acme.takemycar.User.Domain.Model.Queries.GetAllLessorQuery;
import com.acme.takemycar.User.Domain.Model.Queries.GetLessorByIdQuery;

import java.util.List;
import java.util.Optional;

public interface LessorQueryService {
    Optional<Lessor> handle (GetLessorByIdQuery query);
    List<Lessor> handle (GetAllLessorQuery query);
}
