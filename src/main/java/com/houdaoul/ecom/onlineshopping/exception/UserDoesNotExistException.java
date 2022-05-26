package com.houdaoul.ecom.onlineshopping.exception;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException() {
        super("User does not exist!");
    }
}
