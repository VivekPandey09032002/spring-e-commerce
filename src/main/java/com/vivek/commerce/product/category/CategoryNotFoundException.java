package com.vivek.commerce.product.category;

public class CategoryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1259347263707782636L;

	public CategoryNotFoundException(String message) {
        super(message);
    }
}
