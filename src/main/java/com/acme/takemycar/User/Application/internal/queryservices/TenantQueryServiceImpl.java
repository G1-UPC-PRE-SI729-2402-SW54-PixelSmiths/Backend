package com.acme.takemycar.User.Application.internal.queryservices;

import com.acme.takemycar.User.Domain.Model.Aggregates.Lessor;
import com.acme.takemycar.User.Domain.Model.Aggregates.Tenant;
import com.acme.takemycar.User.Domain.Model.Queries.GetAllLessorQuery;
import com.acme.takemycar.User.Domain.Model.Queries.GetAllTenantQuery;
import com.acme.takemycar.User.Domain.Model.Queries.GetLessorByIdQuery;
import com.acme.takemycar.User.Domain.Model.Queries.GetTenantByIdQuery;
import com.acme.takemycar.User.Domain.Services.TenantQueryService;
import com.acme.takemycar.User.Infrastructure.persistence.jpa.repositories.LessorRepository;
import com.acme.takemycar.User.Infrastructure.persistence.jpa.repositories.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantQueryServiceImpl implements TenantQueryService {

    private final TenantRepository tenantRepository;

    public TenantQueryServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Optional<Tenant> handle(GetTenantByIdQuery query) {
        return tenantRepository.findById(query.tenantId());
    }

    @Override
    public List<Tenant> handle(GetAllTenantQuery query) {
        return tenantRepository.findAll();
    }
}
