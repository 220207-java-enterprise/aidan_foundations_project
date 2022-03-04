package com.revature.ers.models;

import com.revature.ers.util.exceptions.InvalidRequestException;

import java.sql.Timestamp;
import java.util.UUID;

public class Reimbursement {
    private String reimbId;
    private Float amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private String paymentId;
    private final Author author = new Author();
    private final Resolver resolver = new Resolver();
    private Status status;
    private Type type;

    public Reimbursement() {
        super();
    }

    public Reimbursement(
        Float amount,
        String description,
        String typeId
    ) {
        this.amount = amount;
        this.description = description;
        this.type = new Type(typeId);

        // generate id
        this.reimbId = UUID.randomUUID().toString();
        // set status to pending
        this.status = new Status("9e10b3e2-734b-4596-a89d-215bd9997691");
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

    public String getAuthorId() {
        return this.author.userId;
    }
    public void setAuthorId(String userId) {
        this.author.userId = userId;
    }

    public String getAuthor() {
        return this.author.username;
    }
    public void setAuthor (String username) {
        this.author.username = username;
    }

    public String getResolverId() {
        return this.resolver.userId;
    }
    public void setResolverId(String userId) {
        this.resolver.userId = userId;
    }

    public String getResolver() {
        return this.resolver.username;
    }
    public void setResolver(String username) {
        this.resolver.username = username;
    }

    public String getStatusId() {
        return this.status.statusId;
    }
    public String getStatus() {
        return this.status.status;
    }
    public void setStatusObj(String statusId) {
        this.status = new Status(statusId);
    }

    public String getTypeId() {
        return this.type.typeId;
    }
    public String getType() {
        return this.type.type;
    }
   public void setTypeObj(String typeId) {
        this.type = new Type(typeId);
   }

   private static class Author {
        private String userId;
        private String username;
   }

   private static class Resolver {
        private String userId;
        private String username;
   }

   private static class Status {
        private final String statusId;
        private final String status;

        private Status(String statusId) {
            this.statusId = statusId;

            switch (statusId) {
                case "9e10b3e2-734b-4596-a89d-215bd9997691":
                    this.status = "pending";
                    break;
                case "65882559-1bab-4306-b99a-7b4c1705b7ef":
                    this.status = "approved";
                    break;
                case "6281dfcf-c602-402a-a878-a0368ca29641":
                    this.status = "denied";
                    break;
                default:
                    throw new InvalidRequestException("StatusId \"" + statusId + "\" is not valid.");
            }
        }
   }

   private static class Type {
        private final String typeId;
        private final String type;

        private Type(String typeId) {
            this.typeId = typeId;

            switch (typeId) {
                case "883b46d2-3339-4c60-a612-4124f8e91201":
                    this.type = "lodging";
                    break;
                case "e1717a12-753e-420e-9e4f-a1d1175ff50d":
                    this.type = "travel";
                    break;
                case "2ffb7e93-6a4b-4b9a-94bd-7df47c368f76":
                    this.type = "food";
                    break;
                case "18b4fac4-5e85-4722-9135-b56a30e9e856":
                    this.type = "other";
                    break;
                default:
                    throw new InvalidRequestException("TypeId \"" + typeId + "\" is not valid.");
            }
        }
    }
}
