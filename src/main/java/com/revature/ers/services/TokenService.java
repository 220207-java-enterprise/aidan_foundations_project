package com.revature.ers.services;

import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.util.auth.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class TokenService {
    private final JwtConfig jwtConfig;

    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                                      .setId(subject.getUserId())
                                      .setIssuer("ers")
                                      .setIssuedAt(new Date(now))
                                      .setExpiration(new Date(now + jwtConfig.getExpiration()))
                                      .setSubject(subject.getUsername())
                                      .claim("role_id", subject.getRoleId())
                                      .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();
    }

    public Principal extractRequesterDetails(String token) {
        try {
            Claims claims = Jwts.parser()
                                .setSigningKey(jwtConfig.getSigningKey())
                                .parseClaimsJws(token)
                                .getBody();

            Principal principal = new Principal();
            principal.setUserId(claims.getId());
            principal.setUsername(claims.getSubject());
            principal.setRoleId(claims.get("role_id", String.class));

            return principal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
