package com.revature.ers.dtos.requests;

import com.revature.ers.models.Reimbursement;

import java.sql.Timestamp;

public class UpdateReimbursementRequest {
    private String reimbId;
    private ReimbursementUpdates updates;

    public String getReimbId() {
        return reimbId;
    }
    public void setReimbId(String reimbId) {
        this.reimbId = reimbId;
    }

    public ReimbursementUpdates getUpdates() {
        return updates;
    }
    public void setUpdates(ReimbursementUpdates updates) {
        this.updates = updates;
    }

    public static class ReimbursementUpdates {
        private Float amount;
        private Timestamp submitted;
        private Timestamp resolved;
        private String description;
        private String paymentId;
        private String AuthorId;
        private String ResolverId;
        private String statusId;
        private String type;

        public Float getAmount() {
            return amount;
        }
        public void setAmount(Float amount) {
            this.amount = amount;
        }

        public Timestamp getSubmitted() {
            return submitted;
        }
        public void setSubmitted(Timestamp submitted) {
            this.submitted = submitted;
        }

        public Timestamp getResolved() {
            return resolved;
        }
        public void setResolved(Timestamp resolved) {
            this.resolved = resolved;
        }

        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        public String getPaymentId() {
            return paymentId;
        }
        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }

        public String getAuthorId() {
            return AuthorId;
        }
        public void setAuthorId(String authorId) {
            AuthorId = authorId;
        }

        public String getResolverId() {
            return ResolverId;
        }
        public void setResolverId(String resolverId) {
            ResolverId = resolverId;
        }

        public String getStatusId() {
            return statusId;
        }
        public void setStatusId(String statusId) {
            this.statusId = statusId;
        }

        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
    }
}
