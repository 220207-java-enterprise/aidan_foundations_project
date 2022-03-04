package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.LoginRequest;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.dtos.requests.UpdateUserRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.dtos.responses.UserResponse;
import com.revature.ers.models.User;
import com.revature.ers.services.TokenService;
import com.revature.ers.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private final UserService userService;
    private final TokenService tokenService;
    private final ObjectMapper mapper;

    public UserServlet(UserService userService, TokenService tokenService, ObjectMapper mapper) {
        this.userService = userService;
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

        // /users/pending
        String[] reqFrags = req.getRequestURI().split("/");
        if (reqFrags.length == 4 && reqFrags[3].equals("pending")) {
            List<UserResponse> users = userService.getPendingUsers();
            resp.getWriter().write(mapper.writeValueAsString(users));
            return;
        }

        // /users
        List<UserResponse> users = userService.getAllUsers();
        resp.getWriter().write(mapper.writeValueAsString(users));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        // /users/login - Login an existing user
        /*
            {
                "username": "username",
                "password": "password"
            }
         */
        String[] reqFrags = req.getRequestURI().split("/");
        if (reqFrags.length == 4 && reqFrags[3].equals("login")) {
            Principal principal = new Principal(
                userService.login(
                    mapper.readValue(req.getInputStream(), LoginRequest.class)
                )
            );
            String jwt = tokenService.generateToken(principal);

            resp.setHeader("Authorization", jwt);
            resp.setStatus(204);
            return;
        }

        // /users - Post a new user
        /*
            {
                "username": "username",
                "email": "email",
                "password": "password",
                "givenName": "givenName",
                "surname": "surname",
                "roleId": "roleId" (optional defaults to employee)
            }
         */
        String jwt = req.getHeader("Authentication");

        NewUserRequest newUserRequest =
            mapper.readValue(req.getInputStream(), NewUserRequest.class);
        Principal principal = null;

        if (jwt != null)
            principal = tokenService.extractRequesterDetails(jwt);

        // if requester is an admin
        if (principal != null && principal.getRoleId().equals("5b21bdca-37f4-468c-9ad2-21b1608f9a6d"))
            newUserRequest.setRequesterIsAdmin(true);

        User newUser = userService.register(newUserRequest);

        if (newUserRequest.getRequesterIsAdmin()) {
            resp.setStatus(201);

            resp.getWriter().write(
                mapper.writeValueAsString(
                    new UserResponse(newUser)
                )
            );
        }
        else
            resp.setStatus(202);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String[] reqFrags = req.getRequestURI().split("/");
        // /users/approve
        /*
            {
               "user_id": "user_id:
            }
        */
        if (reqFrags.length == 4 && reqFrags[3].equals("approve")) {
            String jwt = req.getHeader("Authentication");
            Principal principal = null;

            if (jwt != null)
                principal = tokenService.extractRequesterDetails(jwt);

            if (!(principal != null && principal.getRoleId().equals("5b21bdca-37f4-468c-9ad2-21b1608f9a6d"))) {
                resp.setStatus(403);
                return;
            }

            UpdateUserRequest approveUserRequest = mapper.readValue(
                req.getInputStream(), UpdateUserRequest.class
            );

            userService.approve(approveUserRequest);
            resp.setStatus(201);
        }

    }
}
