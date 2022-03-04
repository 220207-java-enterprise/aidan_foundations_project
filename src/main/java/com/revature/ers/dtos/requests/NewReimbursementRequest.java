package com.revature.ers.dtos.requests;

import com.revature.ers.models.Reimbursement;

public class NewReimbursementRequest {
    private Float amount;
    private String description;
    private String typeId;

    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeId() {
        return typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Reimbursement extractReimbursement() {
        return new Reimbursement(amount, description, typeId);
    }
}
