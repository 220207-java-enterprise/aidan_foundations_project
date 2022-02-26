package com.revature.ers;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.models.Update;
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

        List<String> columnNames = new ArrayList<>();
        columnNames.add("given_name");
        columnNames.add("surname");

        List<String> columnUpdates = new ArrayList<>();
        columnUpdates.add("Billy");
        columnUpdates.add("Bob");

        Update update = new Update(
            "ers_users",
            "user_id",
            "1",
            columnNames,
            columnUpdates
        );

        userDAO.update(update);
    }
}
