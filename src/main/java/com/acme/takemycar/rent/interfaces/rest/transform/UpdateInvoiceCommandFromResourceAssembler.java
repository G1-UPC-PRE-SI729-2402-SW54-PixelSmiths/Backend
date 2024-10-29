package com.acme.takemycar.rent.interfaces.rest.transform;

import com.acme.takemycar.rent.domain.model.commands.UpdateInvoiceCommand;
import com.acme.takemycar.rent.interfaces.rest.resources.UpdateInvoiceResource;

public class UpdateInvoiceCommandFromResourceAssembler {
    public static UpdateInvoiceCommand toCommandFromResource(Long id, UpdateInvoiceResource resource) {
        return new UpdateInvoiceCommand(id, resource.number(), resource.issue_date(),resource.total(),resource.status(),resource.detail());
    }
}
