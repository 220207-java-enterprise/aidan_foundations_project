package com.revature.ers.services;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.LoginRequest;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.dtos.responses.UserResponse;
import com.revature.ers.models.User;
import com.revature.ers.util.exceptions.AuthenticationException;
import com.revature.ers.util.exceptions.InvalidRequestException;
import com.revature.ers.util.exceptions.ResourceConflictException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    final private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User register(NewUserRequest request) {
        User newUser = request.extractUser();

        if (!isUserValid(newUser))
            throw new InvalidRequestException();

        User usernameMatch = userDAO.getByUsername(newUser.getUsername());
        User emailMatch = userDAO.getByEmail(newUser.getEmail());

        if (usernameMatch != null || emailMatch != null) {
            String msg = "The values provided for the following fields are already taken by other users: ";
            if (usernameMatch != null) msg += "username ";
            if (emailMatch != null) msg += "email";
            throw new ResourceConflictException(msg);
        }

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

        if (!isUsernameValid(username) || !isPasswordValid(password))
            throw new InvalidRequestException("Invalid credentials provided");

        User authUser = userDAO.getByUsername(username);

        if (authUser == null || !BCrypt.checkpw(password, authUser.getPassword())) {
            throw new AuthenticationException();
        }

        return authUser;
    }

    private boolean isUserValid(User user) {
        // First name and last name are not just empty strings or filled with whitespace
        if (user.getGivenName().trim().equals("") || user.getSurname().trim().equals(""))
            return false;

        if (!isUsernameValid(user.getUsername())) return false;

        if (!isPasswordValid(user.getPassword())) return false;

        return isEmailValid(user.getEmail());
    }

    private boolean isUsernameValid(String username) {
        if (username == null) return false;
        return username.matches("^[a-zA-Z0-9]{8,25}");
    }

    private boolean isPasswordValid(String password) {
        if (password == null) return false;
        return password.matches("^[^\\s]{8,25}");
    }

    private boolean isEmailValid(String email) {
        if (email == null) return false;
        return email.matches(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])\n"
        );
    }
}
