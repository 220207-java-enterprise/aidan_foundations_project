package com.revature.ers.dtos.requests;

public class UpdateUserRequest {
    private String userId;
    private UserUpdate updates;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserUpdate getUpdates() {
        return updates;
    }
    public void setUpdates(UserUpdate updates) {
        this.updates = updates;
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "userId='" + userId + '\'' +
                ", updates=" + updates +
                '}';
    }

    private static class UserUpdate {
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

        @Override
        public String toString() {
            return "UserUpdate{" +
                    "username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", givenName='" + givenName + '\'' +
                    ", surname='" + surname + '\'' +
                    ", roleId='" + roleId + '\'' +
                    '}';
        }
    }
}
