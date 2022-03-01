package com.revature.ers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.LoginRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.services.UserService;

public class Driver {
    public static void main(String[] args) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("aidanamato");
        loginRequest.setPassword("password");

        System.out.println(new Principal(
            new UserService(new UserDAO()).login(loginRequest)
        ));
    }
}
