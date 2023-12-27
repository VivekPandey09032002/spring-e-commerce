package com.vivek.commerce.product.rating;

import java.io.Serializable;

import com.vivek.commerce.product.Product;
import com.vivek.commerce.user.CommerceUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Rating")
@Getter
@Setter
@ToString(exclude = {"product","user"})
public class Rating  implements Serializable{
	
    private static final long serialVersionUID = 4856747294526971268L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private CommerceUser user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false,name = "rating_value")
    private Double value;
}
