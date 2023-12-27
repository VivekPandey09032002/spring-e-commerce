package com.vivek.commerce.product;

import java.util.Set;

import com.vivek.commerce.product.category.CategoryDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDTO {

        @NotBlank(message = "product title cannot be blank")
        @Size(min = 3, max = 255)
        private String title;

        @NotBlank
        @Size(min = 3, max = 255)
        private String description;

        @Positive
        private double price;

        @PositiveOrZero
        private double discountPrice;

        @Min(0)
        @Max(100)
        private int discountPercent;

        @PositiveOrZero
        private int quantity;

        @NotBlank
        private String brand;


        private String imageUrl;

        @Valid
        private Set<@Valid SizeDTO> sizes;

        @Min(0)
        @Max(5)
        @NotNull
        private Integer rating;

        @NotNull
        private CategoryDTO category;

}
