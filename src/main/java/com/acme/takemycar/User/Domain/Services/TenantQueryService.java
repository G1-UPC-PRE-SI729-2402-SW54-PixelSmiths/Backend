package com.acme.takemycar.User.Domain.Services;

import com.acme.takemycar.User.Domain.Model.Aggregates.Lessor;
import com.acme.takemycar.User.Domain.Model.Aggregates.Tenant;
import com.acme.takemycar.User.Domain.Model.Queries.GetAllLessorQuery;
import com.acme.takemycar.User.Domain.Model.Queries.GetAllTenantQuery;
import com.acme.takemycar.User.Domain.Model.Queries.GetLessorByIdQuery;
import com.acme.takemycar.User.Domain.Model.Queries.GetTenantByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TenantQueryService {
    Optional<Tenant> handle (GetTenantByIdQuery query);
    List<Tenant> handle (GetAllTenantQuery query);
}
