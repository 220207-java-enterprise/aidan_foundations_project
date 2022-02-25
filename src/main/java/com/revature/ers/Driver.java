package com.revature.ers;

import com.revature.ers.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws SQLException {
        Connection myConnection = ConnectionFactory.getInstance().getConnection();
        System.out.println(myConnection.toString());
    }
}
