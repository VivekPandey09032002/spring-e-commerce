package com.vivek.commerce.product.review;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	@Transactional
    @Modifying
    @Query("DELETE FROM Review r WHERE r.product.id = :productId")
    void deleteAllReviewsByProductId(Long productId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Review r WHERE r.user.id = :userId")
    void deleteAllReviewsByUserId(UUID userId);
}
