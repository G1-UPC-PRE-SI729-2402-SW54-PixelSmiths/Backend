package com.acme.takemycar.rent.interfaces.rest.resources;

public record CreateInvoiceResource(String number, String issue_date, Long total, String status, String detail) {
}

