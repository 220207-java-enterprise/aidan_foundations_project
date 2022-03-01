package com.revature.ers;

import org.mindrot.jbcrypt.BCrypt;

public class Driver {
    public static void main(String[] args) {
        String hashedPW1 = BCrypt.hashpw(",J]{V;^-JKY3cxJ[", BCrypt.gensalt(10));
        String hashedPW2 = BCrypt.hashpw(",J]{V;^-JKY3cxJ[", BCrypt.gensalt(10));

        System.out.println("hashedPW1: " + hashedPW1);
        System.out.println("hashedPW2: " + hashedPW2);

        System.out.println("hashedPW1 check: " + BCrypt.checkpw(",J]{V;^-JKY3cxJ[", hashedPW1));
        System.out.println("hashedPW2 check: " + BCrypt.checkpw(",J]{V;^-JKY3cxJ[", hashedPW2));
    }
}
