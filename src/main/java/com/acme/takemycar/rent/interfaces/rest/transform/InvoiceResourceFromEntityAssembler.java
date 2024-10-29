package com.acme.takemycar.rent.interfaces.rest.transform;

import com.acme.takemycar.rent.domain.model.aggregates.Invoice;
import com.acme.takemycar.rent.interfaces.rest.resources.InvoiceResource;

public class InvoiceResourceFromEntityAssembler {
    public static InvoiceResource toResourceFromEntity(Invoice entity) {
        return new InvoiceResource(entity.getId(), entity.getNumber(), entity.getIssue_date(),
                entity.getTotal(),entity.getStatus(),entity.getDetail());
    }
}
