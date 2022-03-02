package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.LoginRequest;
import com.revature.ers.dtos.requests.NewUserRequest;
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
            resp.getWriter().write(mapper.writeValueAsString(principal));
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
                "roleId": "roleId:
            }
         */
        User newUser = userService.register(
            mapper.readValue(req.getInputStream(), NewUserRequest.class)
        );
        resp.setStatus(201);
        resp.getWriter().write(
            mapper.writeValueAsString(
                new UserResponse(newUser)
            )
        );

        // TODO convert to TRY/CATCH block to catch validation related exceptions
    }
}
