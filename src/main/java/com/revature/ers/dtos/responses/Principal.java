package com.revature.ers.dtos.responses;

import com.revature.ers.models.User;
public class Principal {

    private String userId;
    private String username;
    private String roleId;

    public Principal(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.roleId = user.getRoleId();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
