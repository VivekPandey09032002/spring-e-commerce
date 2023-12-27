package com.vivek.commerce.product.category;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {
    @NotBlank(message = "Category name cannot be blank")
    private String name;

    private CategoryDTO parentCategory;

}
