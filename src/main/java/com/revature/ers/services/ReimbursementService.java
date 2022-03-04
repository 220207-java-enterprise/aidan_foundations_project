package com.revature.ers.services;

import com.revature.ers.daos.ReimbursementDAO;
import com.revature.ers.dtos.requests.NewReimbursementRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.util.exceptions.InvalidRequestException;

public class ReimbursementService {
    final private ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public void request(Principal principal, NewReimbursementRequest request) {
        Reimbursement newReimbursement = request.extractReimbursement();
        newReimbursement.setAuthorId(principal.getUserId());

//        if (!isAmountValid || !isDescriptionValid || !isTypeIdValid)
//            throw new InvalidRequestException();

        reimbursementDAO.save(newReimbursement);
    }
}
