package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.LoginRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.dtos.responses.UserResponse;
import com.revature.ers.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private final UserService userService;
    private final ObjectMapper mapper;

    public UserServlet(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
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

        String[] reqFrags = req.getRequestURI().split("/");

        // /users/login
        /*
            {
                "username": "username",
                "password": "password"
            }
         */
        if (reqFrags.length == 4 && reqFrags[3].equals("login")) {
            LoginRequest loginRequest = mapper.readValue(req.getInputStream(), LoginRequest.class);

            // TODO create JWT and set it to Authorization header

            resp.getWriter().write(
                mapper.writeValueAsString(
                    new Principal(
                        userService.login(loginRequest)
                    )
                )
            );
            return;
        }

        resp.setStatus(500);
    }
}
