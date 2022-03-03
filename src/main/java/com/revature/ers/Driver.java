package com.revature.ers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.UpdateUserRequest;

import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        String json =
            "{" +
                "\"userId\": \"12345\"" +
            "}";

        System.out.println("json: " + json);

        try {
            System.out.println(new ObjectMapper().reader().readValue(json, UpdateUserRequest.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
