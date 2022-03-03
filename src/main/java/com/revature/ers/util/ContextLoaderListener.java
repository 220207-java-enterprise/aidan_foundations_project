package com.revature.ers.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.daos.ReimbursementDAO;
import com.revature.ers.daos.UserDAO;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.services.TokenService;
import com.revature.ers.services.UserService;
import com.revature.ers.servlets.ReimbursementServlet;
import com.revature.ers.servlets.UserServlet;
import com.revature.ers.util.auth.JwtConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing Expense Reimbursement System");

        ObjectMapper mapper = new ObjectMapper();
        TokenService tokenService = new TokenService(new JwtConfig());

        UserService userService = new UserService(new UserDAO());
        ReimbursementService reimbursementService = new ReimbursementService(new ReimbursementDAO());

        UserServlet userServlet = new UserServlet(userService, tokenService, mapper);
        ReimbursementServlet reimbursementServlet = new ReimbursementServlet(reimbursementService, tokenService, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet" , userServlet).addMapping("/users/*");
        context.addServlet("ReimbursementServlet", reimbursementServlet).addMapping("/reimbursements/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Shutting down...");
    }
}
