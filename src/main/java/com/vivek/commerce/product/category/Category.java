package com.vivek.commerce.product.category;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Category implements Serializable {
    private static final long serialVersionUID = -7264653291035595723L;

	@Column(name = "category_name",nullable = false)
    @Id
    private String name;

	@ManyToOne
	@JoinColumn(name = "parent_category")
    private Category parentCategory;
}
