package com.houdaoul.ecom.onlineshopping.exception;

public class ProductDoesNotExistException extends RuntimeException{

    public ProductDoesNotExistException() {
        super("Product Does Not Exist!");
    }
}
