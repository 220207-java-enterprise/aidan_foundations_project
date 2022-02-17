package com.revature.ers;

import com.revature.ers.models.UserRole;

public class Driver {
    public static void main(String[] args) {
        UserRole user = new UserRole("1", "boss man");
        System.out.println(user.getRole());
    }
}
