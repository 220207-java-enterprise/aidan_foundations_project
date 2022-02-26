package com.revature.ers;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.models.User;

public class Driver {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        User testUser = new User(
            "1",
            "aidanamato",
            "aidan@mail.com",
            "PASSWORD",
            "Aidan",
            "Amato",
            "1"
        );

        // userDAO.save(testUser);

        System.out.println(userDAO.getById("1").toString());
    }
}
