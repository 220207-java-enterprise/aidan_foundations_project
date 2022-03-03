package com.revature.ers.services;

import com.revature.ers.daos.ReimbursementDAO;

public class ReimbursementService {
    final private ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }


}
