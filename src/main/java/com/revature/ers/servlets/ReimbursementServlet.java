package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.NewReimbursementRequest;
import com.revature.ers.dtos.requests.UpdateReimbursementRequest;
import com.revature.ers.dtos.requests.UpdateUserRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.dtos.responses.ReimbursementResponse;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.services.TokenService;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
    private final ReimbursementService reimbursementService;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String jwt = req.getHeader("Authentication");
        Principal principal = null;

        if (jwt != null)
            principal = tokenService.extractRequesterDetails(jwt);

        if (principal == null) {
            resp.setStatus(401);
            return;
        } else if (!principal.getRoleId().equals("5b21bdca-37f4-468c-9ad2-21b1608f9a6d")) {
            resp.setStatus(403);
            return;
        }

        // reimbursements/pending
        String[] reqFrags = req.getRequestURI().split("/");
        if (reqFrags.length == 4 && reqFrags[3].equals("pending")) {
            List<ReimbursementResponse> reimbs = reimbursementService.getPendingReimbs();
            resp.getWriter().write(mapper.writeValueAsString(reimbs));
            return;
        }

        resp.setStatus(501);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // /reimbursements
        /*
            {
                "amount": 12.34,
                "description": "description",
                "typeId": "typeId"
            }
         */

        String jwt = req.getHeader("Authentication");

        NewReimbursementRequest newReimbursementRequest =
            mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);
        Principal principal = null;

        if (jwt == null) {
            resp.setStatus(401);
            throw new AuthenticationException("Not logged in.");
        }
        else
            principal = tokenService.extractRequesterDetails(jwt);

        reimbursementService.request(principal, newReimbursementRequest);
        resp.setStatus(202);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] reqFrags = req.getRequestURI().split("/");
        // /reimbursements/status
        /*
            {
               "reimbId": "reimbId",
               "updates": {
                    "statusId": "statusId"
               }
            }
        */
        if (reqFrags.length == 4 && reqFrags[3].equals("status")) {
            String jwt = req.getHeader("Authentication");
            Principal principal = null;

            if (jwt != null)
                principal = tokenService.extractRequesterDetails(jwt);

            if (principal == null) {
                resp.setStatus(401);
                return;
            } else if (!principal.getRoleId().equals("5b21bdca-37f4-468c-9ad2-21b1608f9a6d")) {
                resp.setStatus(403);
                return;
            }

            UpdateReimbursementRequest approveReimbRequest = mapper.readValue(
                    req.getInputStream(), UpdateReimbursementRequest.class
            );
            approveReimbRequest.getUpdates().setResolverId(principal.getUserId());

            reimbursementService.setStatus(approveReimbRequest);
            resp.setStatus(204);
        }
    }
}