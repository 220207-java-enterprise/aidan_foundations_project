package com.revature.ers.services;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.LoginRequest;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.dtos.requests.UpdateUserRequest;
import com.revature.ers.dtos.responses.UserResponse;
import com.revature.ers.models.User;
import com.revature.ers.util.exceptions.AuthenticationException;
import com.revature.ers.util.exceptions.InvalidRequestException;
import com.revature.ers.util.exceptions.ResourceConflictException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Map<String, Object> usernameParam = new HashMap<>();
        usernameParam.put("username", newUser.getUsername());
        List<User> usernameMatches = userDAO.getByParams(usernameParam);


        Map<String, Object> emailParam = new HashMap<>();
        emailParam.put("email", newUser.getEmail());
        List<User> emailMatches = userDAO.getByParams(emailParam);

        if (usernameMatches.size() != 0 || emailMatches.size() != 0) {
            String msg = "The values provided for the following fields are already taken by other users: ";
            if (usernameMatches.size() != 0) msg += "username ";
            if (emailMatches.size() != 0) msg += "email";
            throw new ResourceConflictException(msg);
        }

        if (request.getRequesterIsAdmin()) {
            newUser.setIsApproved(true);
            newUser.setIsActive(true);
        }

        newUser.setPassword(
            BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt(10))
        );

        userDAO.save(newUser);
        return newUser;
    }

    public void approve(UpdateUserRequest update) {
        userDAO.approve(update);
    }

    public User login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (!isUsernameValid(username) || !isPasswordValid(password))
            throw new InvalidRequestException("Invalid credentials provided");

        Map<String, Object> usernameParam = new HashMap<>();
        usernameParam.put("username", username);
        List<User> authUserList = userDAO.getByParams(usernameParam);

        if (authUserList.size() == 0)
            throw new AuthenticationException();

        User authUser = authUserList.get(0);

        if (!authUser.getIsActive() || !BCrypt.checkpw(password, authUser.getPassword()))
            throw new AuthenticationException();

        return authUser;
    }

    public List<UserResponse> getAllUsers() {
        return userDAO.getAll()
                      .stream()
                      .map(UserResponse::new)
                      .collect(Collectors.toList());
    }

    public List<UserResponse> getPendingUsers() {
        Map<String, Object> pendingParam = new HashMap<>();
        pendingParam.put("is_approved", false);

        return userDAO.getByParams(pendingParam)
                      .stream()
                      .map(UserResponse::new)
                      .collect(Collectors.toList());
    }

    public boolean isUserValid(User user) {
        // First name and last name are not just empty strings or filled with whitespace
        if (user.getGivenName().trim().equals("") || user.getSurname().trim().equals(""))
            return false;

        if (!isUsernameValid(user.getUsername())) {
            return false;
        }

        if (!isPasswordValid(user.getPassword())) {
            return false;
        }

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
        return email.matches("^[^@\\s]+@[^@\\s.]+\\.[^@.\\s]+$");
    }
}
