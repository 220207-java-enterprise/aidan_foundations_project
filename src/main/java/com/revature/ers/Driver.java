package com.revature.ers;

import com.revature.ers.daos.ReimbursementDAO;
import com.revature.ers.daos.UserDAO;
import com.revature.ers.models.User;

import java.util.HashMap;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        HashMap<String, Object> searchParams = new HashMap<>();
        searchParams.put("status_id", "65882559-1bab-4306-b99a-7b4c1705b7ef");
        System.out.println(new ReimbursementDAO().getByParams(searchParams).toString());
    }
}
