package com.vivek.commerce.product;

public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -9183955246863369389L;

	public ProductNotFoundException(String message) {
        super(message);
    }
}
