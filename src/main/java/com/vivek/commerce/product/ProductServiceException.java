package com.vivek.commerce.product;

public class ProductServiceException extends RuntimeException {
    private static final long serialVersionUID = -1100597560605941460L;

	public ProductServiceException(String message) {
        super(message);
    }
}
