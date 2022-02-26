package com.revature.ers;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.models.User;

import java.util.List;
import java.util.ArrayList;

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

        // System.out.println(userDAO.getById("1").toString());
        List<String> columnNames = new ArrayList<>();

        columnNames.add("given_name");
        columnNames.add("surname");

        String updateQuery = userDAO.createUpdateQuery("ers_users", "user_id", "1", columnNames);
        System.out.println(updateQuery);
    }
}
