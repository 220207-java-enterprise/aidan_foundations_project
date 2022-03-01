package com.revature.ers.services;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.LoginRequest;
import com.revature.ers.dtos.responses.UserResponse;
import com.revature.ers.models.User;
import com.revature.ers.util.exceptions.AuthenticationException;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    final private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<UserResponse> getAllUsers() {
        return userDAO.getAll()
                      .stream()
                      .map(UserResponse::new)
                      .collect(Collectors.toList());
    }

    public User login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // TODO validate credentials here

        // TODO encrypt password (password in DB will be encrypted)

        User authUser = userDAO.getByUsernameAndPassword(username, password);

        if (authUser == null) {
            throw new AuthenticationException();
        }

        return authUser;
    }
}
