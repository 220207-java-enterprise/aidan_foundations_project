package com.revature.ers.models;

import com.revature.ers.util.exceptions.InvalidRequestException;

import java.util.UUID;

public class User {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String givenName;
    private String surname;
    private Boolean isApproved;
    private Boolean isActive;
    private UserRole userRole;

    public User() {
        // this is for you Wezley
        super();
    }

    public User(
        String username,
        String email,
        String password,
        String givenName,
        String surname,
        String roleId
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.userRole = new UserRole(roleId);

        // user will automatically be instantiated with random id
        this.userId = UUID.randomUUID().toString();
        // and as inactive as they wait for admin approval
        this.isApproved = false;
        this.isActive = false;
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

    public Boolean getIsApproved() {
        return isApproved;
    }
    public void setIsApproved(Boolean approved) {
        isApproved = approved;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getRoleId() {
        return this.userRole.roleId;
    }
    public String getRole() {
        return this.userRole.role;
    }
    public void setUserRoleObj(String roleId) {
        this.userRole = new UserRole(roleId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", isApproved=" + isApproved +
                ", isActive=" + isActive +
                ", userRole=" + userRole +
                '}';
    }

    private static class UserRole {
        private String roleId;
        private String role;

        private UserRole (
                String roleId
        ) {
            this.roleId = roleId;

            switch (roleId) {
                case "1714d958-bffd-4fa8-965c-c96c130bba2f":
                    this.role = "employee";
                    break;
                case "df59b48c-0928-46eb-92b3-e07f27dadc3c":
                    this.role = "finance_manager";
                    break;
                case "5b21bdca-37f4-468c-9ad2-21b1608f9a6d":
                    this.role = "admin";
                    break;
                default:
                    throw new InvalidRequestException("RoleID \"" + roleId + "\" is not valid.");
            }
        }

        @Override
        public String toString() {
            return "UserRole{" +
                    "roleId='" + roleId + '\'' +
                    ", role='" + role + '\'' +
                    '}';
        }
    }
}