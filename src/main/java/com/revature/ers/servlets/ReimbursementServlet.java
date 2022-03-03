package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.services.TokenService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReimbursementServlet extends HttpServlet {
    private final ReimbursementService  reimbursementService;
    private final TokenService tokenService;
    private final ObjectMapper mapper;

    public ReimbursementServlet(
            ReimbursementService reimbursementService,
            TokenService tokenService,
            ObjectMapper mapper
    ) {
        this.reimbursementService = reimbursementService;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
