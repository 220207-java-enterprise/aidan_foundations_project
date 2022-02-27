package com.revature.ers.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.daos.UserDAO;
import com.revature.ers.services.UserService;
import com.revature.ers.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing Expense Reimbursement System");

        ObjectMapper mapper = new ObjectMapper();

        UserService userService = new UserService(new UserDAO());

        UserServlet userServlet = new UserServlet(userService, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet" , userServlet).addMapping("/users/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Shutting down...");
    }
}
