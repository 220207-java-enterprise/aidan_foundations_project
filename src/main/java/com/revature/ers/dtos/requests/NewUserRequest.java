package com.revature.ers.dtos.requests;

import com.revature.ers.models.User;

public class NewUserRequest {
    private String username;
    private String email;
    private String password;
    private String givenName;
    private String surname;
    private String roleId;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGivenName() {
        return givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public User extractUser() {
        // role defaults to employee
        if (roleId == null) {
            roleId = "1714d958-bffd-4fa8-965c-c96c130bba2f";
        }

        return new User(
            username,
            email,
            password,
            givenName,
            surname,
            roleId
        );
    }
}
