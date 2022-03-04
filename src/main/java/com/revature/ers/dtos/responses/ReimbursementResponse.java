package com.revature.ers.dtos.responses;

import com.revature.ers.models.Reimbursement;

import java.sql.Timestamp;

public class ReimbursementResponse {
    private String reimbId;
    private Float amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private String payment_id;
    private Author author;
    private Resolver resolver;
    private Status status;
    private Type type;

    public ReimbursementResponse (Reimbursement reimbursement) {
        this.reimbId = reimbursement.getReimbId();
        this.amount = reimbursement.getAmount();
        this.submitted = reimbursement.getSubmitted();
        this.resolved = reimbursement.getResolved();
        this.description = reimbursement.getDescription();
        this.payment_id = reimbursement.getPaymentId();
        this.author = new Author(
            reimbursement.getAuthorId(),
            reimbursement.getAuthor()
        );
        this.resolver = new Resolver(
            reimbursement.getResolverId(),
            reimbursement.getResolver()
        );
        this.status = new Status(
            reimbursement.getStatusId(),
            reimbursement.getStatus()
        );
        this.type = new Type(
            reimbursement.getTypeId(),
            reimbursement.getType()
        );
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

    public Author getAuthor() {
        return author;
    }

    public Resolver getResolver() {
        return resolver;
    }

    public Status getStatus() {
        return status;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ReimbursementResponse{" +
                "reimbId='" + reimbId + '\'' +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", payment_id='" + payment_id + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", status=" + status +
                ", type=" + type +
                '}';
    }

    private static class Author {
        private String userId;
        private String username;

        public Author(String userId, String username) {
            this.userId = userId;
            this.username = username;
        }

        public String getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public String toString() {
            return "Author{" +
                    "userId='" + userId + '\'' +
                    ", username='" + username + '\'' +
                    '}';
        }
    }

    private static class Resolver {
        private String userId;
        private String username;

        public Resolver(String userId, String username) {
            this.userId = userId;
            this.username = username;
        }

        public String getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public String toString() {
            return "Resolver{" +
                    "userId='" + userId + '\'' +
                    ", username='" + username + '\'' +
                    '}';
        }
    }

    private static class Status {
        private String statusId;
        private String status;

        public Status(String statusId, String status) {
            this.statusId = statusId;
            this.status = status;
        }

        public String getStatusId() {
            return statusId;
        }

        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "Status{" +
                    "statusId='" + statusId + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }

    private static class Type {
        private String statusId;
        private String status;

        public Type(String statusId, String status) {
            this.statusId = statusId;
            this.status = status;
        }

        public String getStatusId() {
            return statusId;
        }

        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "Type{" +
                    "statusId='" + statusId + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}
