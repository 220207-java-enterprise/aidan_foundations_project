package com.revature.ers;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws SQLException {
        UserDAO userDAO = new UserDAO();

        System.out.println(userDAO.getAll().toString());
    }
}
