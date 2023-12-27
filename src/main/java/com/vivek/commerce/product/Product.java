package com.vivek.commerce.product;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vivek.commerce.product.category.Category;
import com.vivek.commerce.product.rating.Rating;
import com.vivek.commerce.product.review.Review;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product implements Serializable {

	private static final long serialVersionUID = -2528661053437002284L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "desc", nullable = false)
	private String description;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "discount_price")
	private Double discountPrice = 0.0;

	@Column(name = "discount_percent")
	private Integer discountPercent = 0;

	private Integer quantity = 1;

	@Column(name = "product_brand", nullable = false)
	private String brand;

	@Column(name = "image_url")
	private String imageUrl;

	@Embedded
	@ElementCollection
	@CollectionTable(name = "product_size", joinColumns = @JoinColumn(name = "product_id"))
	private Set<Size> sizes = new HashSet<>();

	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private List<Rating> ratings = new ArrayList<>();

	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();

	@Column(name = "rating", nullable = false)
	private int rating;

	@ManyToOne
	@JoinColumn(name = "category_name", nullable = false)
	private Category category;

	@Column(name = "created_time", nullable = false)
	private LocalDate createdTime;

	@PrePersist
	public void prePersist() {
		if (createdTime == null) {
			createdTime = LocalDate.now();
		}
	}

}