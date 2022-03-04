package com.revature.ers.services;

import com.revature.ers.daos.ReimbursementDAO;
import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.NewReimbursementRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.dtos.responses.ReimbursementResponse;
import com.revature.ers.models.Reimbursement;

import java.util.List;
import java.util.stream.Collectors;

public class ReimbursementService {
    final private ReimbursementDAO reimbursementDAO;
    final private UserDAO userDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO, UserDAO userDAO) {
        this.reimbursementDAO = reimbursementDAO;
        this.userDAO = userDAO;
    }

    public void request(Principal principal, NewReimbursementRequest request) {
        Reimbursement newReimbursement = request.extractReimbursement();
        newReimbursement.setAuthorId(principal.getUserId());
        newReimbursement.setAuthor(principal.getUsername());

//        if (!isAmountValid || !isDescriptionValid)
//            throw new InvalidRequestException();

        reimbursementDAO.save(newReimbursement);
    }

    public List<ReimbursementResponse> getPendingReimbs() {
        return reimbursementDAO.getPending()
                               .stream()
                               .map(ReimbursementResponse::new)
                               .collect(Collectors.toList());
    }
}
