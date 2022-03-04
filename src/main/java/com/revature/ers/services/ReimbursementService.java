package com.revature.ers.services;

import com.revature.ers.daos.ReimbursementDAO;
import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.NewReimbursementRequest;
import com.revature.ers.dtos.requests.UpdateReimbursementRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.dtos.responses.ReimbursementResponse;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.Update;
import com.revature.ers.util.exceptions.InvalidRequestException;

import java.util.ArrayList;
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

    public void setStatus(UpdateReimbursementRequest update) {
        UpdateReimbursementRequest.ReimbursementUpdates updates = update.getUpdates();
        String statusId = updates.getStatusId();

        if (
            !statusId.equals("65882559-1bab-4306-b99a-7b4c1705b7ef") &&
            !statusId.equals("6281dfcf-c602-402a-a878-a0368ca29641")
        )
            throw new InvalidRequestException("Invalid status id provided.");


        reimbursementDAO.updateStatus(update);
    }
}
