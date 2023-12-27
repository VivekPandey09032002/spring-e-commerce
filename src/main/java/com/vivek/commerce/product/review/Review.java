package com.vivek.commerce.product.review;

import com.vivek.commerce.product.Product;
import com.vivek.commerce.user.CommerceUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "review")
@ToString(exclude = {"product","user"})
public class Review  implements Serializable {
	
    private static final long serialVersionUID = -5526820634491129063L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int rating;
    
    private String comment;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private CommerceUser user;

}
