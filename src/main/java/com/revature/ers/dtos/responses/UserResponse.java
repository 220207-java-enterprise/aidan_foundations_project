package com.revature.ers.dtos.responses;

import com.revature.ers.models.User;

public class UserResponse {
    private final String userId;
    private final String username;
    private final String email;
    private final String givenName;
    private final String surname;
    private final String role;

    public UserResponse(User user) {
        userId = user.getUserId();
        username = user.getUsername();
        email = user.getEmail();
        givenName = user.getGivenName();
        surname = user.getSurname();
        role = user.getRole();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
