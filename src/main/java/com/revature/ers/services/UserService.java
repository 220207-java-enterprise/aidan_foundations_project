package com.revature.ers.services;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.LoginRequest;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.dtos.responses.UserResponse;
import com.revature.ers.models.User;
import com.revature.ers.util.exceptions.AuthenticationException;
import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    final private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User register(NewUserRequest request) {
        User newUser = request.extractUser();

        // TODO Validate request details

        // TODO check availability of username and email

        newUser.setPassword(
            BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt(10))
        );

        userDAO.save(newUser);
        return newUser;
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

        password = BCrypt.hashpw(password, BCrypt.gensalt(10));

        User authUser = userDAO.getByUsernameAndPassword(username, password);

        if (authUser == null) {
            throw new AuthenticationException();
        }

        return authUser;
    }
}
