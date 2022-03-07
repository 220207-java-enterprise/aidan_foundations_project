package com.revature.ers;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.models.User;

import java.util.HashMap;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        HashMap<String, Object> searchParams = new HashMap<>();
        searchParams.put("is_active", true);
        System.out.println(userDAO.getByParams(searchParams).toString());
    }
}
