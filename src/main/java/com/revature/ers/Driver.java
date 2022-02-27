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
import java.util.UUID;

public class Driver {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++ ) {
            System.out.println(UUID.randomUUID());
        }
    }
}
