package com.houdaoul.ecom.onlineshopping.exception;

public class CartDoesNotExistException extends RuntimeException {

    public CartDoesNotExistException() {
        super("Cart does not exist!");
    }
}
