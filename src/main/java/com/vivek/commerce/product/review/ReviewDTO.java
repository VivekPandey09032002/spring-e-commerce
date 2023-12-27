package com.vivek.commerce.product.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewDTO {
    @Min(1)
    @Max(5)
    private int rating;
    @NotBlank
    @Size(max = 255)
    private String comment;
}
