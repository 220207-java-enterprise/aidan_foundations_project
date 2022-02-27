package com.revature.ers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.responses.UserResponse;
import com.revature.ers.models.Update;
import com.revature.ers.models.User;
import com.revature.ers.services.UserService;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        UserService userService = new UserService(new UserDAO());

        List<UserResponse> users = userService.getAllUsers();
        System.out.println(users.toString());

        try {
            System.out.println(mapper.writeValueAsString(users));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
