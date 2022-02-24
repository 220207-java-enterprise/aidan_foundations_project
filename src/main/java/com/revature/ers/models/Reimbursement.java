package com.revature.ers.models;

import java.sql.Timestamp;

public class Reimbursement {
    private String reimbId;
    private Float amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private String paymentId;
    private String resolverId;
    private String statusId;
    private String typeId;

    public Reimbursement(
        String reimbId,
        Float amount,
        Timestamp submitted,
        Timestamp resolved,
        String description,
        String paymentId,
        String resolverId,
        String statusId,
        String typeId
    ) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.paymentId = paymentId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public String getReimbId() {
        return this.reimbId;
    }
    public void setReimbId(String reimbId) {
        this.reimbId = reimbId;
    }

    public Float getAmount() {
        return this.amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return this.submitted;
    }
    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return this.resolved;
    }
    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentId() {
        return this.paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getResolverId() {
        return this.resolverId;
    }
    public void setResolverId(String resolverId) {
        this.resolverId = resolverId;
    }

    public String getStatusId() {
        return this.statusId;
    }
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTypeId() {
        return this.typeId;
    }
    public void setTypeId() {
        this.typeId = typeId;
    }
}
