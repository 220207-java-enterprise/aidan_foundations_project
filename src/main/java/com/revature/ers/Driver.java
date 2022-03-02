package com.revature.ers;

import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.models.User;
import com.revature.ers.services.TokenService;
import com.revature.ers.util.auth.JwtConfig;

public class Driver {
    public static void main(String[] args) {
       Principal principal = new Principal(
           new User(
               "aidanamato",
               "password",
               "aidan@mail.com",
               "Aidan",
               "Amato",
               "4321"
           )
       );

       JwtConfig jwtConfig = new JwtConfig();
       TokenService tokenService = new TokenService(jwtConfig);

       String jwt = tokenService.generateToken(principal);
       System.out.println(tokenService.extractRequesterDetails(jwt).toString());
    }
}
