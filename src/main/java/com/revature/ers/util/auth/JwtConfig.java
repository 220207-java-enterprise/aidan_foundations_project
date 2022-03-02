package com.revature.ers.util.auth;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Properties;

public class JwtConfig {
    private String salt;
    private int expiration = 60 * 60 * 1000; // one hour
    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
    private final Key signingKey;

    public JwtConfig() {
        try {
            Properties props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            props.load(loader.getResourceAsStream("application.properties"));

            salt = props.getProperty("secret");
        } catch (IOException e) {
            e.getStackTrace();
        }

        byte[] pretzels = DatatypeConverter.parseBase64Binary(salt);
        signingKey = new SecretKeySpec(pretzels, sigAlg.getJcaName());
    }

    public int getExpiration() {
        return expiration;
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }
}
