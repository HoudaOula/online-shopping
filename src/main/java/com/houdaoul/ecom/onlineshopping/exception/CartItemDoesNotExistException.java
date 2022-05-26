package com.houdaoul.ecom.onlineshopping.exception;

public class CartItemDoesNotExistException extends RuntimeException{

    public CartItemDoesNotExistException() {
        super("CartItem Does Not Exist!");
    }
}
