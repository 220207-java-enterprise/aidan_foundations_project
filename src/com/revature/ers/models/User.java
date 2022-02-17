package com.revature.ers.models;

public class User {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String givenName;
    private String surname;
    private Boolean isActive;
    private String roleId;

    public User(
        String userId,
        String username,
        String email,
        String password,
        String givenName,
        String surname,
        String roleId
    ) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.roleId = roleId;

        // user will automatically be instantiated as active
        this.isActive = true;
    }

    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGivenName() {
        return this.givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return this.surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }
    public void toggleIsActive() {
        this.isActive = !this.isActive;
    }

    public String getRoleId() {
        return this.roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
