package com.revature.ers.dtos.responses;

import java.sql.Timestamp;

public class ReimbursementResponse {
    private String reimbId;
    private Float amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private String payment_id;
    private String author_id;
    private String resolver_id;
    private String status_id;
    private String type_id;

    public ReimbursementResponse (
            String reimbId,
            Float amount,
            Timestamp submitted,
            String description,
            String author_id,
            String status_id,
            String type_id
    ) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.description = description;
        this.author_id = author_id;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public ReimbursementResponse (
            String reimbId,
            Float amount,
            Timestamp submitted,
            Timestamp resolved,
            String description,
            String payment_id,
            String author_id,
            String resolver_id,
            String status_id,
            String type_id
    ) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.payment_id = payment_id;
        this.author_id = author_id;
        this.resolver_id = resolver_id;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public String getReimbId() {
        return reimbId;
    }

    public Float getAmount() {
        return amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public String getDescription() {
        return description;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public String getResolver_id() {
        return resolver_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public String getType_id() {
        return type_id;
    }
}
