package com.revature.ers;

import org.mindrot.jbcrypt.BCrypt;

public class Driver {
    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("p4$$w0r17", BCrypt.gensalt(10)));
    }
}
