package com.revature.ers.util.exceptions;

public class RoleIdException extends RuntimeException {
    public RoleIdException(String roleId) {
        super("RoleID \"" + roleId + "\" is not a valid id");
    }
}
