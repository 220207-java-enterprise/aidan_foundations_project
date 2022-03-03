package com.revature.ers.dtos.responses;

import com.revature.ers.models.User;
public class Principal {

    private String userId;
    private String username;
    private String roleId;

    public Principal() {
        // This is for you Wezley
        super();
    }

    public Principal(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.roleId = user.getRoleId();
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
