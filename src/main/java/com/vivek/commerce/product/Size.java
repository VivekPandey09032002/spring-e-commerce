package com.vivek.commerce.product;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Size implements Serializable {
	
    private static final long serialVersionUID = 7725481245644148971L;
    
	private String name;
    private String quantity;
}
