package com.revature.ers;

import com.revature.ers.models.User;
import com.revature.ers.models.UserRole;

public class Driver {
    public static void main(String[] args) {
       User guy = new User(
               "1",
               "guy",
               "guy@email.com",
               "password",
               "guy",
               "sir",
               "1"
       );
       System.out.println(guy.getIsActive());
       guy.toggleIsActive();
       System.out.println(guy.getIsActive());
    }
}
