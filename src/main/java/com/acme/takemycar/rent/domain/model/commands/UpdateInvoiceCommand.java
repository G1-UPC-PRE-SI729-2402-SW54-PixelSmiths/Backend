package com.acme.takemycar.rent.domain.model.commands;

public record UpdateInvoiceCommand(Long id, String number, String issue_date, Long total, String status, String detail) {
}
