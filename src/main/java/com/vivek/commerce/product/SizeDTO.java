package com.vivek.commerce.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SizeDTO {

    @NotBlank(message = "Size name cannot be blank")
    private String name;

    @NotBlank(message = "Size quantity cannot be blank")
    private String quantity;
}